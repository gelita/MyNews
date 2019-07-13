package com.fanikiosoftware.mynews.controllers.activities;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.fanikiosoftware.mynews.R;

public class MyAlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "MyAlarmReceiver";
    public static final int NOTIFICATION_ID = 222;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Alarm Receiver executing");
        //todo run query and then notify
        Intent notificationIntent = new Intent(context, QueryResultsActivity.class);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(
                context,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle(context.getString(R.string.notify_title))
                .setContentText(context.getString(R.string.read_now_question))
                .setAutoCancel(true)
                .setContentIntent(pendingNotificationIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}