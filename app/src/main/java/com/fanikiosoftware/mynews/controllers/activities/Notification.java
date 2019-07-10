package com.fanikiosoftware.mynews.controllers.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

public class Notification extends AppCompatActivity {

    private static final String TAG = "Notification";

    //set alarm intent so that app runs search 1x daily
    public static void setAlarm(Context context, ArrayList<String> notificationQueryList) {
        Log.d(TAG, "setting alarm...");
        Intent intent = new Intent(context, MyAlarmReceiver.class);
        intent.putStringArrayListExtra("notificationQuery", notificationQueryList);
        //pIntent grants permission to external applications to act on intent
        PendingIntent pIntent = PendingIntent.getBroadcast(
                context,
                100,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(System.currentTimeMillis());
        //user HOUR_OF_DAY for 24 hr clock & set to 9 for 9am
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        //add 1 day to the calendar instance in order to prevent alarm from being called for past
        //scheduled intent
        calendar.add(Calendar.DATE, 1);
        //RTC fires the pending intent at the specific time but does not wake up the device.
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.cancel(pIntent);
        alarmMgr.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pIntent
        );
        Log.d(TAG, "Alarm set!");
    }
}