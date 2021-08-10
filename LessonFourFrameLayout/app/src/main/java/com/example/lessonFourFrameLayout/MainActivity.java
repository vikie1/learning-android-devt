package com.example.lessonFourFrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //This applies to switch too
    public void toggleForget(View view) {
        TextView forgetText = (TextView) findViewById(R.id.forget);
        boolean on = ((ToggleButton) view).isChecked();
        if (on) forgetText.setText("(I will not forget)");
        else forgetText.setText("(I will forget)");
    }
}