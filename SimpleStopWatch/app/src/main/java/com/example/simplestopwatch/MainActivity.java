package com.example.simplestopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private volatile int seconds = 0;
    private boolean isRunning;
    private boolean wasRunning;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //retrieve saved state if present
        if (savedInstanceState != null){
            synchronized (this){
                seconds = savedInstanceState.getInt("time");
            }
            isRunning = savedInstanceState.getBoolean("isRunning");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        delayForASecond();
    }

    //set running to true to start stop watch
    public void startCount(View view) {
        isRunning = true;
    }

    //set running to false to stop the stop watch
    public void stopCount(View view) {
        isRunning = false;
    }

    //set running to false and return timer to zero
    public void resetCount(View view) {
        isRunning = false;
        synchronized (this){
            seconds = 0;
        }
    }

    //update the view to show elapsed time
    private void runTimer(){
        TextView timeView = (TextView) findViewById(R.id.view_time);
        int hours = seconds/3600;
        int minutes = (seconds%3600)/60;
        int secs = seconds%60;
        String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours, minutes, secs);
        timeView.setText(time);
        timeView.setTextColor(Color.WHITE);
    }

    //run every second to simulate a timer
    private void delayForASecond(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                runTimer();
                handler.postDelayed(this, 1000);
                synchronized (this) {
                    if (isRunning){
                        seconds++;
                    }
                }
            }
        };
        runnable.run();
    }

    //save the timer to persist after configuration changes e.g screen rotate
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("time", seconds);
        outState.putBoolean("isRunning", isRunning);
        outState.putBoolean("wasRunning", wasRunning);

    }

    //stop the timer if the activity is no longer visible
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (isRunning) {
//            wasRunning = true;
//        }
//        isRunning = false;
//    }

    //resume counter once the activity regains visibility
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (wasRunning){
//            isRunning = true;
//        }
//    }

    //pause the timer if the app is still visible but doesn't have primary focus e.g during stupid pop-ups
    @Override
    protected void onPause() {
        super.onPause();
        if (isRunning) {
            wasRunning = true;
        }
        isRunning = false;
    }

    //resuming the timer when the pop-up is gone or app regain focus by some other means
    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning){
            isRunning = true;
        }
    }
}