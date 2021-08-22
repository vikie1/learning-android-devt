package com.example.odometer;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class OdometerService extends Service {

    public OdometerService() {}

    private final IBinder binder = new OdometerBinder();
    private LocationListener locationListener;
    public static final String PERMISSION_STRING = Manifest.permission.ACCESS_FINE_LOCATION;
    private LocationManager locationManager;
    private static double distanceInMeters;
    private Location lastLocation = null;

    @Override
    public IBinder onBind(Intent intent) { return binder; }

    @Override
    public void onCreate() {
        super.onCreate();

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                if(lastLocation == null) lastLocation = location;
                distanceInMeters += location.distanceTo(lastLocation);
                lastLocation = location;
            }
        };

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, PERMISSION_STRING) == PackageManager.PERMISSION_GRANTED){
            String locationProvider = locationManager.getBestProvider(new Criteria(), true);
            if (locationProvider != null) locationManager.requestLocationUpdates(locationProvider, 1000, 1, locationListener);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null && locationListener != null){
            if (ContextCompat.checkSelfPermission(this, PERMISSION_STRING) == PackageManager.PERMISSION_GRANTED)
                locationManager.removeUpdates(locationListener);
            locationManager = null;
            locationListener = null;
        }
    }

    public double getDistance(){ return distanceInMeters / 1609.344; }

    public class OdometerBinder extends Binder{ OdometerService getOdometer(){ return OdometerService.this; }}
}