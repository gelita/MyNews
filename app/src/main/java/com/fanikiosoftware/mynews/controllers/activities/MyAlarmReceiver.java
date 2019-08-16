package com.fanikiosoftware.mynews.controllers.activities;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.fanikiosoftware.mynews.R;
import com.fanikiosoftware.mynews.controllers.network.NewsApi;
import com.fanikiosoftware.mynews.controllers.network.SearchResponse;
import com.fanikiosoftware.mynews.controllers.utility.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

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
    private static final String CHANNEL_ID = "channel";
    private static final int NOTIFICATION_ID = 0;
    private ArrayList<String> userQueryList = null;
    private int numHits;

    @Override
    public void onReceive(Context context, Intent intent) {
        //get user's query from the intent extra and then run Query
        userQueryList = getExtras(intent);
        createNotificationChannel(context);
        //check if there are articles and if so, send notification
        //if articles exist--> notify user of articles
        runQuery(context, userQueryList); //returns the number of hits #articles returned in the query
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

    private void runQuery(final Context context, ArrayList<String> userQueryList) {
        //if articles returned -> notify user
        Gson gson = new GsonBuilder().serializeNulls().create();
        HttpLoggingInterceptor loggingInterceptor;
        loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        //retrofit will create the body of the method being called w/out a defn in NewsApi.class
        NewsApi newsApi = retrofit.create(NewsApi.class);
        //get the user query that was in the arguments of the bundle
        String query = userQueryList.get(0);
        StringBuilder section = new StringBuilder();
        if (userQueryList.size() > 1) {
            //get sections from list starting at 2nd item on list(1st item is user's query)
            for (int i = 1; i < userQueryList.size(); i++) {
                section.append(userQueryList.get(i)).append(",");
            }
        }
//        String start = null;
//        String end = null;
        Call<SearchResponse> call = newsApi.getDocs(query, section.toString(), null, null, Constants.API_KEY);
        assert call != null;
        //network call for Search
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(@NotNull Call<SearchResponse> call, @NotNull Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getDocsResponse() != null) {
                            numHits = response.body().getDocsResponse().getMeta().getNumHits();
                            if (numHits > 0) {
                                notifyThis(context);
                            } else {
                                //no articles returned then Toast user
                                Toast.makeText(
                                        context,
                                        context.getResources().getString(R.string.notify_user_no_articles_found),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<SearchResponse> call, @NotNull Throwable t) {
            }
        });
    }

    private void notifyThis(Context context) {
        Intent notificationIntent = new Intent(context, QueryResultsActivity.class);
        notificationIntent.putStringArrayListExtra(Constants.USER_QUERY_LIST, userQueryList);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context)
                .addNextIntent(notificationIntent);
        PendingIntent pIntent = stackBuilder.getPendingIntent(22, PendingIntent.FLAG_UPDATE_CURRENT);
        @SuppressLint("IconColors") NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_nyt_notification)
                .setContentTitle("Your New York Times articles are ready.")
                .setContentText("Read now?")
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