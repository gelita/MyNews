package com.fanikiosoftware.mynews.controllers.activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.print.PrintDocumentAdapter;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.fanikiosoftware.mynews.R;
import com.fanikiosoftware.mynews.controllers.network.NewsApi;
import com.fanikiosoftware.mynews.controllers.network.SearchResponse;
import com.fanikiosoftware.mynews.controllers.utility.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.v4.app.NotificationCompat.CATEGORY_RECOMMENDATION;

public class MyAlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "MyAlarmReceiver";
    public static final String CHANNEL_ID = "channel";
    public static final int NOTIFICATION_ID = 0;
    ArrayList<String> userQueryList = null;
    NewsApi newsApi;
    public boolean isArticles = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "executing");
        //get user's query from the intent extra and then run Query
        userQueryList = getExtras(intent);
        createNotificationChannel(context);
        //check if there are articles and if so, send notification
        runQuery(userQueryList);
        //if articles exist--> notify user of articles
        Log.d(TAG, " " + isArticles);
//        if(isArticles) {
            notifyThis(context, "Your New York Times articles are ready.", "Read now?");
//        }
    }

    private ArrayList<String> getExtras(Intent intent) {
        return intent.getStringArrayListExtra(Constants.USER_QUERY_LIST);
    }

    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getResources().getString(R.string.channel_name);
            String description = context.getResources().getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void runQuery(ArrayList<String> userQueryList) {
        //if articles returned -> notify user
        Log.d(TAG, "loading JSON");
        Gson gson = new GsonBuilder().serializeNulls().create();
        HttpLoggingInterceptor loggingInterceptor;
        loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        //retrofit will create the body of the method being called w/out a defn in NewsApi.class
        newsApi = retrofit.create(NewsApi.class);
        //get the user query that was in the arguments of the bundle
        String query = userQueryList.get(0);
        String section = "";
        Log.d(TAG, "userQueryList.size() = " + userQueryList.size());
        if (userQueryList.size() > 1) {
            //get sections from list starting at 2nd item on list(1st item is user's query)
            for (int i = 1; i < userQueryList.size(); i++) {
                section += userQueryList.get(i) + ",";
            }
        }
        Log.d(TAG, "query = " + query + ", section = " + section + "api-key: " + Constants.API_KEY);
        Call<SearchResponse> call = newsApi.getDocs(query, section, Constants.API_KEY);
        assert call != null;
        Log.d(TAG, "starting Search network call");
        //network call for Search
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getDocsResponse() != null) {
                            isArticles = true;
                            int numHits = response.body().getDocsResponse().getDocsList().size();
                            Log.d(TAG, "numHits = " + numHits);
                            if(numHits > 0){
                                isArticles = true;
                                Log.d(TAG, "isArticles = true");
                            }
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                isArticles = false;
                Log.d(TAG, "isArticles still false");
            }
        });
    }

    public void notifyThis(Context context, String title, String message) {
        Intent notificationIntent = new Intent(context, QueryResultsActivity.class);
        notificationIntent.putStringArrayListExtra(Constants.USER_QUERY_LIST, userQueryList);
        Log.d(TAG, "sending user notification");
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(notificationIntent);
        PendingIntent pIntent = stackBuilder.getPendingIntent(22, PendingIntent.FLAG_UPDATE_CURRENT);
        Log.d(TAG, "creating notification builder");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
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
        manager.notify(NOTIFICATION_ID, builder.build());
    }
}