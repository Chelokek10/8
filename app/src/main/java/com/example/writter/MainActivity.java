package com.example.writter;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication1.R;



public class MainActivity extends AppCompatActivity {
    private Button flowerbutton;
    private Button starsrbutton;
    private FrameLayout frameLayout;
    private FlowerFragment flowerFragment = new FlowerFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ініціалізація елементів
        flowerbutton = (Button) findViewById (R.id.flowerbutton);
        starsrbutton = (Button) findViewById (R.id.starsrbutton);
        frameLayout = findViewById (R.id.frameLayout);

        flowerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(flowerFragment);
            }
        });

        starsrbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new StarsFragment());
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

}
