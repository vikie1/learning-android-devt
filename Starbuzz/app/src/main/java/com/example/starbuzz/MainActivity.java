package com.example.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.starbuzz.storage.StarbuzzDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private Cursor favouritesCursor;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupOptionsListView();
        setupFavouritesListView();
    }

    //populate the list_favourite listView from cursor
    private void setupFavouritesListView() {
        ListView listFavourites = (ListView) findViewById(R.id.list_favourites);
        SQLiteOpenHelper helper = new StarbuzzDatabaseHelper(this);
        try {
            database = helper.getReadableDatabase();
            favouritesCursor = database.query("DRINK",
                    new String[]{"_id", "Name"},
                    "FAVOURITE = 1",
                    null,null,null,null);
            CursorAdapter favouriteAdapter = new SimpleCursorAdapter(MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    favouritesCursor,
                    new String[]{"Name"},
                    new int[]{android.R.id.text1}, 0);
            listFavourites.setAdapter(favouriteAdapter);
        }catch (SQLException e){
            Toast toast = Toast.makeText(this, "Could not get favourites data", Toast.LENGTH_LONG);
            toast.show();
        }

        //Navigate to drinkActivity if drink is clicked
        listFavourites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int) id);
                startActivity(intent);
            }
        });
    }

    private void setupOptionsListView() {

        //create a click listener for listView items
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(MainActivity.this, DrinkCategoryActivity.class);
                    startActivity(intent);
                }else {
                    Toast toast = Toast.makeText(MainActivity.this, "Functionality has not been added yet", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        };
        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(listener);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Cursor newCursor = database.query("DRINK",
                new String[]{"_id", "Name"},
                "FAVOURITE = 1",
                null,null,null,null);
        ListView favourites = (ListView) findViewById(R.id.list_favourites);
        CursorAdapter cursorAdapter = (CursorAdapter) favourites.getAdapter();
        cursorAdapter.changeCursor(newCursor);
        favouritesCursor = newCursor;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        favouritesCursor.close();
        database.close();
    }
}