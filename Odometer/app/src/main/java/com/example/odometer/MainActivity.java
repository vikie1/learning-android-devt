package com.example.odometer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private OdometerService odometerService;
    private boolean bound = false;
    private final int PERMISSION_REQUEST_CODE = 6419;
    //private final int NOTIFICATION_ID = 423;

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            OdometerService.OdometerBinder odometerBinder = (OdometerService.OdometerBinder) service;
            odometerService = odometerBinder.getOdometer();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) { bound = false; }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayDistance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, OdometerService.PERMISSION_STRING) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{OdometerService.PERMISSION_STRING},
                    PERMISSION_REQUEST_CODE
            );
        } else{
            Intent intent = new Intent(this, OdometerService.class);
            bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(this, OdometerService.class);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);

                Toast toast = Toast.makeText(this, "Gatchu", Toast.LENGTH_LONG);
                toast.show();
            }else {
                Toast toast = Toast.makeText(this, "Fuck you", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound){
            unbindService(serviceConnection);
            bound = false;
        }
    }

    private void displayDistance(){
        TextView textView = (TextView) findViewById(R.id.distance);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                double distance = 0.0;
                if (bound && odometerService != null) distance = odometerService.getDistance();
                String distanceStr = String.format(Locale.getDefault(), "%1$, .2f miles", distance);
                textView.setText(distanceStr);
                handler.postDelayed(this, 1000);
            }
        });
    }
}