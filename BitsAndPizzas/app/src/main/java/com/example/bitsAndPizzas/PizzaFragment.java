package com.example.bitsAndPizzas;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.bitsAndPizzas.controller.CaptionedImagesAdapter;
import com.example.bitsAndPizzas.util.Pizza;

import java.util.Objects;

public class PizzaFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView pizzaRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_pizza, container, false);
        String[] pizzaNames = new String[Pizza.pizzas.length];
        for (int i = 0; i < pizzaNames.length ; i++) {
            pizzaNames[i] = Pizza.pizzas[i].getName();
        }
        int[] pizzaImages = new int[Pizza.pizzas.length];
        for (int i = 0; i < pizzaImages.length ; i++) {
            pizzaImages[i] = Pizza.pizzas[i].getImageResourceId();
        }
        CaptionedImagesAdapter captionedImagesAdapter = new CaptionedImagesAdapter(pizzaNames, pizzaImages);
        pizzaRecycler.setAdapter(captionedImagesAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        pizzaRecycler.setLayoutManager(gridLayoutManager);
        captionedImagesAdapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), PizzaActivity.class);
                intent.putExtra(PizzaActivity.EXTRA_PIZZA_ID, position);
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });
        return pizzaRecycler;
    }
}