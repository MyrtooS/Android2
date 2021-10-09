package com.example.it21771_assignment2;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class ConnectivityReceiver extends android.content.BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent serviceIntent = new Intent(context, LocationService.class);


        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

                context.startService(serviceIntent);
                Toast.makeText(context, "Wifi connection", Toast.LENGTH_LONG).show();

            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {


                context.startService(serviceIntent);
                Toast.makeText(context, "Mobile data connection", Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show();
            context.stopService(serviceIntent);

        }


    }


}



