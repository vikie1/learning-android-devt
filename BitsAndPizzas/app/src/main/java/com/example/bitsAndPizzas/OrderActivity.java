package com.example.bitsAndPizzas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onClickDone(View view) {
        CharSequence text = "Your order is done";
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), text, BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setAction("toast", new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(OrderActivity.this, "Am heare as the toast", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        snackbar.show();
    }
}