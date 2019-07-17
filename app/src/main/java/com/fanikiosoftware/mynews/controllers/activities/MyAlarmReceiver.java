package com.fanikiosoftware.mynews.controllers.activities;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
        userQueryList = getExtras(intent);
        //runQuery(userQueryList);
        //if articles exist--> notify user of articles
        notifyThis(context, "Your New York Times articles are ready.", "Read now?");
    }

    private ArrayList<String> getExtras(Intent intent) {
        return intent.getStringArrayListExtra("userQueryList");
    }

//    private void runQuery(ArrayList<String> query) {
//        //if articles returned -> notify user
//        Boolean articles = false;
//        //run the query
//        if(articles) {
//            //send notification
//        }else{
//            //todo exit and do nothing
//        }
//    }


    public void notifyThis(Context context, String title, String message) {
        Intent mIntent = new Intent(context, QueryResultsActivity.class);
//        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //add user query as extra
        mIntent.putStringArrayListExtra("userQuery", userQueryList);
        Log.d(TAG, "list: " + userQueryList);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, mIntent, 0);
        Log.d(TAG, "creating notification builder");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.nyt_notification)
                .setContentTitle(title)
                .setContentText(message)
                //auto cancel removes notification automatically when user taps it
                .setAutoCancel(true)
                //sets the type of notification for system use(example: when DO NOT DISTURB is on)
                .setCategory(CATEGORY_RECOMMENDATION)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setContentIntent(pIntent)
                .setOnlyAlertOnce(true)
                .setOngoing(false);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(0, mBuilder.build());
    }
}