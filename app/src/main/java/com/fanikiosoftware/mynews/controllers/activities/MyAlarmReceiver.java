package com.fanikiosoftware.mynews.controllers.activities;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.fanikiosoftware.mynews.R;

public class MyAlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "MyAlarmReceiver";
    public static final int NOTIFICATION_ID = 222;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Alarm Receiver executing");
        //todo run query and then notify
        sendNotification(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void sendNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        String NOTIFICATION_CHANNEL_ID = "33";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel =
                    new NotificationChannel(
                            NOTIFICATION_CHANNEL_ID,
                            "My Notifications",
                            NotificationManager.IMPORTANCE_MAX
                    );
            // Configure the notification channel.
            notificationChannel.setDescription("MyNewsChannel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(R.color.colorBrightAccent);
            notificationChannel.enableVibration(false);
            notificationManager.createNotificationChannel(notificationChannel);
            Intent notificationIntent = new Intent(context, QueryResultsActivity.class);
            PendingIntent pendingNotificationIntent = PendingIntent.getActivity(
                    context,
                    100,
                    notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(android.R.drawable.sym_def_app_icon)
                    .setContentTitle(context.getString(R.string.notify_title))
                    .setContentText(context.getString(R.string.read_now_question))
                    .setAutoCancel(true)
                    .setContentIntent(pendingNotificationIntent);
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }
}