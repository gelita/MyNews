package com.fanikiosoftware.mynews.controllers.activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.fanikiosoftware.mynews.R;

import java.util.ArrayList;

import static android.support.v4.app.NotificationCompat.CATEGORY_RECOMMENDATION;

public class MyAlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "MyAlarmReceiver";
    public static final String CHANNEL_ID = "channel";
    ArrayList<String> userQueryList = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "executing");
        //get user's query from the intent extra and then run Query
        runQuery(getExtras(intent));
        //if articles exist--> notify user of articles
        createNotificationChannel(context);
        createPendingIntent(context);
        notifyThis(context, "Your New York Times articles are ready.", "Read now?");
    }

    private ArrayList<String> getExtras(Intent intent) {
        return intent.getStringArrayListExtra("userQueryList");
    }

    private void runQuery(ArrayList<String> query) {

    }

    private PendingIntent createPendingIntent(Context context) {
        //This is the intent of PendingIntent
        Intent intent = new Intent(context, QueryResultsActivity.class);
        //This is optional if you have more than one buttons and want to differentiate between two
        intent.putStringArrayListExtra("userQuery", userQueryList);
        PendingIntent pendingIntent = null;
        return pendingIntent.getBroadcast(
                context,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel1";
            String description = "channel descrip";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void notifyThis(Context context, String title, String message) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.nyt_notification)
                .setContentTitle(title)
                .setContentText(message)
                //notification automatically removed when clicked
                .setAutoCancel(true)
                //sets the type of notification for system use(example: when DO NOT DISTURB is on)
                .setCategory(CATEGORY_RECOMMENDATION)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setColor(ContextCompat.getColor(context, R.color.colorBrightAccent))
                .addAction(R.drawable.ic_help, "Read articles now", createPendingIntent(context))
                .setOngoing(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, mBuilder.build());
    }
}