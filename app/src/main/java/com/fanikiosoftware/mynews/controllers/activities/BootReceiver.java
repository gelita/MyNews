package com.fanikiosoftware.mynews.controllers.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.fanikiosoftware.mynews.controllers.utility.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BootReceiver extends BroadcastReceiver {

    private static final String TAG = BootReceiver.class.getSimpleName();
    private SharedPreferences mPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        assert action != null;
        if (action.equals("android.intent.action.BOOT_COMPLETED")) {
            mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            String queryArray = mPreferences.getString(Constants.USER_QUERY_LIST, "");
            Log.d(TAG,queryArray);
            List<String> queries = Arrays.asList(queryArray.split(","));
            ArrayList<String> userQueryList = new ArrayList<>();
            for (String query : queries) {
                userQueryList.add(query);
            }
            NotificationActivity.setAlarm(context, userQueryList);
            Log.d(TAG, "uql = " + userQueryList);
            Log.d(TAG, "reboot: alarm set");
        }
    }
}