package com.example.lessontwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveMessageActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);
        Intent intent = getIntent();
        getMessage(intent);
    }

    private void getMessage(Intent intent){
        String message = intent.getStringExtra(Intent.EXTRA_TEXT);
        updateView(message);
    }

    private void updateView(String displayText){
        TextView textView = (TextView) findViewById(R.id.userTextDisplay);
        textView.setText(displayText);
    }
}