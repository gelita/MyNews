package com.fanikiosoftware.mynews.controllers.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fanikiosoftware.mynews.controllers.utility.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BootReceiver extends BroadcastReceiver {

    private static final String TAG = BootReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        assert action != null;
        if (action.equals("android.intent.action.BOOT_COMPLETED")) {
            SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            String queryArray = mPreferences.getString(Constants.USER_QUERY_LIST, "");
            List<String> queries = Arrays.asList(Objects.requireNonNull(queryArray).split(","));
            ArrayList<String> userQueryList = new ArrayList<>(queries);
            NotificationActivity.setAlarm(context, userQueryList);
        }
    }
}