package com.fanikiosoftware.mynews.controllers.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fanikiosoftware.mynews.controllers.utility.Constants;

import java.util.ArrayList;
import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity {

    private static final String TAG = "Notification";

    //set alarm intent so that app runs search 1x daily
    public static void setAlarm(Context context, ArrayList<String> userQueryList) {
        Log.d(TAG, "setting alarm.....");
        //alarm going off will trigger MyAlarmReceiver
        Intent alarmIntent = new Intent(context, MyAlarmReceiver.class);
        //sending the query in order to be able to add it in pendingIntent for the notification
        alarmIntent.putStringArrayListExtra(Constants.USER_QUERY_LIST, userQueryList);
        Log.d(TAG, Constants.USER_QUERY_LIST + " ln 26: " + userQueryList);
        //pIntent grants permission to external applications to act on intent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                100,
                alarmIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
//        user HOUR_OF_DAY for 24 hr clock & set to 9 for 9am
        calendar.setTimeInMillis(System.currentTimeMillis());
        //commented out fot testing /demo purposes
//        calendar.set(Calendar.HOUR_OF_DAY, 9);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
        //+1day to the calendar instance to prevent alarm from being called for past scheduled intent
//        calendar.add(Calendar.DATE, 1);

        calendar.add(Calendar.SECOND, 15);
        //RTC fires the pending intent at the specific time but does not wake up the device.
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.cancel(pendingIntent);
        alarmMgr.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        );
        Log.d(TAG, "Alarm set!");
    }
}