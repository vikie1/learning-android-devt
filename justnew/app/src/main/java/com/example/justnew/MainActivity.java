package com.example.justnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    BeerExpert advice = new BeerExpert();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickFindBeer(View view){
        TextView brands = (TextView) findViewById(R.id.brands);
        Spinner color = (Spinner) findViewById(R.id.color);
        String beerType = String.valueOf(color.getSelectedItem());
        List<String> adviceBrands= advice.getBrands(beerType);
        StringBuilder brandsList = new StringBuilder();
        for (String brand : adviceBrands){
            brandsList.append(brand).append("\n");
        }
        brands.setText(brandsList);
    }
}