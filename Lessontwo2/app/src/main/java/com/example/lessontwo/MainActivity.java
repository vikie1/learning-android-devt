package com.example.lessontwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchNewActivity(View view) {
        EditText editText = (EditText) findViewById(R.id.userInput);
        //Intent intent = new Intent(this, ReceiveMessageActivity.class);
        String userInput = editText.getText().toString();
        //intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, userInput);
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, userInput);
            Intent newIntent = Intent.createChooser(intent, "Select app to use dummy ...");
            startActivity(newIntent);
            //startActivity(intent);
        }catch (ActivityNotFoundException e){
            return;
        }
    }
}