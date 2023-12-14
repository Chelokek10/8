package com.example.writter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication1.R;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;



import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;

import java.util.ArrayList;
import java.util.List;

public class FlowerFragment extends Fragment {
    private FlowerDatabaseHelper dbHelper;
    private EditText editTextFlowerName;
    private Button buttonSearch;
    private TextView textViewResult;
    private Button buttonShowList;
    private RecyclerView recyclerViewFlowers;
    private FlowerAdapter flowerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flower, container, false);

        dbHelper = new FlowerDatabaseHelper(getActivity());
        dbHelper.copyDatabaseFromAssets();

        editTextFlowerName = view.findViewById(R.id.editTextFlowerName);
        buttonSearch = view.findViewById(R.id.buttonSearch);
        textViewResult = view.findViewById(R.id.textViewResult);
        buttonShowList = view.findViewById(R.id.buttonShowList);
        recyclerViewFlowers = view.findViewById(R.id.recyclerViewFlowers);

        // Ініціалізація RecyclerView та встановлення адаптера
        List<Flower> flowerList = getAllFlowers();
        flowerAdapter = new FlowerAdapter(flowerList);
        recyclerViewFlowers.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewFlowers.setAdapter(flowerAdapter);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flowerName = editTextFlowerName.getText().toString();
                Flower searchedFlower = searchFlower(flowerName);
                if (searchedFlower != null) {
                    String result = "Name: " + searchedFlower.getName() + "\nDescription: " + searchedFlower.getDescription();
                    textViewResult.setText(result);
                } else {
                    textViewResult.setText("Flower not found");
                }
            }
        });

        // Додано обробник для кнопки "Список квітів"
        buttonShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFlowerList(v);
            }
        });

        return view;
    }
        // Метод для додавання квітки до бази даних
        private void addFlower (String name, String description){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(FlowerDatabaseHelper.COLUMN_NAME, name);
            values.put(FlowerDatabaseHelper.COLUMN_DESCRIPTION, description);
            db.insert(FlowerDatabaseHelper.TABLE_NAME, null, values);
            db.close();
        }

        // Метод для пошуку квітки за назвою
        private Flower searchFlower (String name){
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String[] projection = {FlowerDatabaseHelper.COLUMN_ID, FlowerDatabaseHelper.COLUMN_NAME, FlowerDatabaseHelper.COLUMN_DESCRIPTION};

            String selection = FlowerDatabaseHelper.COLUMN_NAME + " = ?";
            String[] selectionArgs = new String[]{name};
            Cursor cursor = db.query(
                    FlowerDatabaseHelper.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            Flower flower = null;
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(FlowerDatabaseHelper.COLUMN_ID));
                String flowerName = cursor.getString(cursor.getColumnIndexOrThrow(FlowerDatabaseHelper.COLUMN_NAME));
                String flowerDescription = cursor.getString(cursor.getColumnIndexOrThrow(FlowerDatabaseHelper.COLUMN_DESCRIPTION));
                flower = new Flower(id, flowerName, flowerDescription);
            }

            cursor.close();
            db.close();
            return flower;
        }

        // Метод для отримання всіх квітів з бази даних
        private List<Flower> getAllFlowers () {
            List<Flower> flowerList = new ArrayList<>();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] projection = {FlowerDatabaseHelper.COLUMN_ID, FlowerDatabaseHelper.COLUMN_NAME, FlowerDatabaseHelper.COLUMN_DESCRIPTION};

            Cursor cursor = db.query(
                    FlowerDatabaseHelper.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(FlowerDatabaseHelper.COLUMN_ID));
                String flowerName = cursor.getString(cursor.getColumnIndexOrThrow(FlowerDatabaseHelper.COLUMN_NAME));
                String flowerDescription = cursor.getString(cursor.getColumnIndexOrThrow(FlowerDatabaseHelper.COLUMN_DESCRIPTION));
                flowerList.add(new Flower(id, flowerName, flowerDescription));
            }

            cursor.close();
            db.close();
            return flowerList;
        }

        // Метод для відображення списку квітів
        public void showFlowerList(View view) {
            List<Flower> flowerList = getAllFlowers();
            recyclerViewFlowers.setVisibility(View.VISIBLE);
            flowerAdapter = new FlowerAdapter(flowerList);
            recyclerViewFlowers.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewFlowers.setAdapter(flowerAdapter);
        }

}