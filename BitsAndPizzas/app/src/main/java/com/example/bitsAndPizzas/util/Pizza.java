package com.example.bitsAndPizzas.util;

import com.example.bitsAndPizzas.R;

public class Pizza {

    private String name;
    private int imageResourceId;

    public static final Pizza[] pizzas = {
            new Pizza("Diavolo", R.drawable.html),
            new Pizza("Funghi", R.drawable.update)
    };

    public Pizza(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
