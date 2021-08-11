package com.example.learnintents;

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
    public  void onSendMessage(View view){
        EditText messageView = (EditText) findViewById(R.id.message);
        String messageText = messageView.getText().toString();
        //Intent msg = new Intent(this, ReceiveMessageActivity.class);
        //msg.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE,messageText);
        Intent msg = new Intent(Intent.ACTION_SEND);
        msg.setType("text/plain");
        msg.putExtra(Intent.EXTRA_TEXT,messageText);
        try {
            startActivity(msg);
        }catch (ActivityNotFoundException e){
            Toast toast = Toast.makeText(this, "No activity can do that in your system", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}