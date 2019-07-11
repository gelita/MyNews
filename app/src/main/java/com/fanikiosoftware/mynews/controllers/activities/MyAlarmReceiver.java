package com.fanikiosoftware.mynews.controllers.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MyAlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "MyAlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Alarm Receiver executing");
        Bundle extras = intent.getExtras();
        ArrayList<String> query = extras.getStringArrayList("notificationsQuery");
    }
}