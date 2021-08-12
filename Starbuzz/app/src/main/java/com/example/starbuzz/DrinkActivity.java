package com.example.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.starbuzz.data.Drinks;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINKID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //get drink from the intent
        int drinkId = (Integer)getIntent().getExtras().get(EXTRA_DRINKID);
        Drinks drinks = Drinks.drinks[drinkId];

        //populate drink name
        TextView nameView = (TextView) findViewById(R.id.name);
        nameView.setText(drinks.getName());

        //populate drink description
        TextView descriptionView = (TextView) findViewById(R.id.description);
        descriptionView.setText(drinks.getDescription());

        //populate drink photo
        ImageView photoView = (ImageView) findViewById(R.id.photo);
        photoView.setImageResource(drinks.getImgResourceId());
        photoView.setContentDescription(drinks.getName());
    }
}