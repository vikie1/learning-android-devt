package com.example.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.starbuzz.data.Drinks;
import com.example.starbuzz.storage.StarbuzzDatabaseHelper;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINKID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

//        //get drink from the intent
        int drinkId = (Integer)getIntent().getExtras().get(EXTRA_DRINKID);
//        Drinks drinks = Drinks.drinks[drinkId];

        /*
          Getting drink from database now
          */
        //get an instance of the database
        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase sqLiteDatabase = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.query("DRINK",
                    new String[]{"Name", "Description", "Image_id"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkId)},
                    null, null, null);
            if (cursor.moveToFirst()){

                //get drink details from cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int imageId = cursor.getInt(2);

                //populate drink name
                TextView name = (TextView) findViewById(R.id.name);
                name.setText(nameText);

                //populate drink description
                TextView description = (TextView) findViewById(R.id.description);
                description.setText(descriptionText);

                //populate drink picture
                ImageView picture = (ImageView) findViewById(R.id.photo);
                picture.setImageResource(imageId);
                picture.setContentDescription(nameText);
            }
            cursor.close();
            sqLiteDatabase.close();
        }catch (SQLException e){
            Toast toast = Toast.makeText(this, "Database access problem", Toast.LENGTH_LONG);
            toast.show();
        }

//        //populate drink name
//        TextView nameView = (TextView) findViewById(R.id.name);
//        nameView.setText(drinks.getName());
//
//        //populate drink description
//        TextView descriptionView = (TextView) findViewById(R.id.description);
//        descriptionView.setText(drinks.getDescription());
//
//        //populate drink photo
//        ImageView photoView = (ImageView) findViewById(R.id.photo);
//        photoView.setImageResource(drinks.getImgResourceId());
//        photoView.setContentDescription(drinks.getName());
    }
}