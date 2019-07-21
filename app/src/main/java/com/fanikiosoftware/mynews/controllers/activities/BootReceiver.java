package com.fanikiosoftware.mynews.controllers.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {

    private static final String TAG = BootReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //verify that intent rec'd is in fact the one that is registered in the manifest
        assert action != null;
        if (action.equals("android.intent.action.BOOT_COMPLETED")) {

//            NotificationActivity.setAlarm(context, userQueryList);
            Log.d(TAG, "reboot: alarm set");
        }
    }
}