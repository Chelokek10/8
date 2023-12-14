package com.example.writter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication1.R;


public class StarsFragment extends Fragment {

    View view;
    Button starsrbutton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_flower, container, false);
        view = inflater.inflate(R.layout.fragment_stars, container, false);
        starsrbutton = (Button) view.findViewById(R.id.starsrbutton);

        starsrbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Stars Fragment", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}