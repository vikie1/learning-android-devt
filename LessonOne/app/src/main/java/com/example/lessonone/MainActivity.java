package com.example.lessonone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lessonone.util.BeerExpert;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final BeerExpert beerExpert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //called when the user clicks the find_beer button
    public void findBeer(View view){
        TextView brandsTextView = (TextView) findViewById(R.id.brands);
        Spinner brandColor = (Spinner) findViewById(R.id.color);
        String selectedColor = String.valueOf(brandColor.getSelectedItem());
        List<String> brands = beerExpert.getBrands(selectedColor);
        String userBrandsView = "";
        for (String brand : brands){
            userBrandsView += brand + '\n';
            brandsTextView.setText(userBrandsView);
        }
    }
}