package com.example.starbuzz.data;

import com.example.starbuzz.R;

public class Drinks {
	private final String name;
	private final String description;
	private final int imgResourceId;

	//A hardcoded array of drinks
	public static final Drinks[] drinks = {
	        new Drinks("Latte", "A couple of espresso shots with steamed milk", R.drawable.latte),
            new Drinks("Cappuccino", "Espresso, hot milk and a steamed milk foam", R.drawable.cappuccino),
            new Drinks("Filter", "Highest quality beans roasted and brewed fresh", R.drawable.filter)
	};

    public Drinks(String name, String description, int imgResourceId) {
        this.name = name;
        this.description = description;
        this.imgResourceId = imgResourceId;
    }

    public int getImgResourceId() {
        return imgResourceId;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return this.name;
    }
}
