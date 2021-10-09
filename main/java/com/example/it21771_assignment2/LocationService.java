package com.example.it21771_assignment2;

import android.Manifest;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


public class LocationService extends Service {


    final LocationHelper helper = new LocationHelper(this);
    private double lon;
    private double lat;
    private long timestamp;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(getApplicationContext(), "service started", Toast.LENGTH_SHORT).show();

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(getApplicationContext(), location.getLatitude() + " " + location.getLongitude() + " " +location.getTime(), Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("content://com.example.it21771.assignment2.ContentProvider/locations");
                ContentResolver resolver = getContentResolver();

                lat = location.getLatitude();
                lon = location.getLongitude();
                timestamp = location.getTime();

                ContentValues values = new ContentValues();
                values.put(LocationHelper.KEY_LON, lon);
                values.put(LocationHelper.KEY_LAT, lat);
                values.put(LocationHelper.KEY_TIMESTAMP, timestamp);
                resolver.insert(uri, values);



            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });


        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "service stopped", Toast.LENGTH_SHORT).show();
    }
}
