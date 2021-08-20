package com.example.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.starbuzz.data.Drinks;
import com.example.starbuzz.storage.StarbuzzDatabaseHelper;

public class DrinkCategoryActivity extends AppCompatActivity {

    private SQLiteDatabase database;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);
        //ArrayAdapter<Drinks> drinksArrayAdapter = new ArrayAdapter<>(this,  android.R.layout.simple_list_item_1, Drinks.drinks);
        ListView listDrinks = (ListView) findViewById(R.id.list_drinks);
        SQLiteOpenHelper databaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            database = databaseHelper.getReadableDatabase();
            cursor = database.query("DRINK",
                    new String[]{"_id", "Name"},
                    null, null, null, null, null);
            SimpleCursorAdapter drinksArrayAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"Name"},
                    new int[]{android.R.id.text1}, 0);
            listDrinks.setAdapter(drinksArrayAdapter);
        }catch (SQLException e){
            Toast toast = Toast.makeText(this, "Database access problem", Toast.LENGTH_LONG);
            toast.show();
        }
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID,(int) id);
                startActivity(intent);
            }
        };
        listDrinks.setOnItemClickListener(onItemClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        database.close();
    }
}