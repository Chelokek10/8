package com.example.writter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;

import java.util.List;

// Адаптер для RecyclerView, який відповідає за відображення списку квітів
public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder> {
    private List<Flower> flowerList; // Список квітів, які будуть відображені

    // Конструктор адаптера
    public FlowerAdapter(List<Flower> flowerList) {
        this.flowerList = flowerList;
    }

    // Метод для створення нового елемента RecyclerView
    @NonNull
    @Override
    public FlowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Створення нового елемента, використовуючи макет із item_flower.xml
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flower, parent, false);
        return new FlowerViewHolder(itemView);
    }

    // Метод для зв'язування даних квітки із конкретним елементом RecyclerView
    @Override
    public void onBindViewHolder(@NonNull FlowerViewHolder holder, int position) {
        Flower flower = flowerList.get(position);
        // Встановлення тексту для TextView на основі даних квітки
        holder.textViewName.setText(flower.getName());
        holder.textViewDescription.setText(flower.getDescription());
    }

    // Метод для отримання кількості елементів у списку
    @Override
    public int getItemCount() {
        return flowerList.size();
    }

    // Клас для представлення елемента RecyclerView (ViewHolder)
    public class FlowerViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName, textViewDescription;

        // Конструктор ViewHolder
        public FlowerViewHolder(View view) {
            super(view);
            // Ініціалізація TextView для назви та опису квітки
            textViewName = view.findViewById(R.id.textViewName);
            textViewDescription = view.findViewById(R.id.textViewDescription);
        }
    }
}
