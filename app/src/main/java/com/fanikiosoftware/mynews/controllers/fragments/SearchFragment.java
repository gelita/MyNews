package com.fanikiosoftware.mynews.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanikiosoftware.mynews.R;
import com.fanikiosoftware.mynews.controllers.activities.SearchAdapter;
import com.fanikiosoftware.mynews.controllers.network.Docs;
import com.fanikiosoftware.mynews.controllers.network.NewsApi;
import com.fanikiosoftware.mynews.controllers.network.SearchResponse;
import com.fanikiosoftware.mynews.controllers.utility.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import icepick.Icepick;
import icepick.State;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    public TextView textViewResult;
    SearchAdapter adapter;
    NewsApi newsApi;
    @State
    int position;
    ArrayList<String> userQueryList;
    RecyclerView recyclerView;
    List<Docs> docsList = new ArrayList<>();
    public static final String TAG = "SearchFragment";

    public static SearchFragment newInstance(int position, ArrayList<String> userQueryList) {
        Log.d(TAG, "newInstance");
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putStringArrayList("userQueryList", userQueryList);
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called");
        //Get activity_query identifier from abstract method declared in child class
        //this method will report the correct activity_query's identifier so the correct activity_query will be used
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        textViewResult = rootView.findViewById(R.id.tv_textView);
        recyclerView = rootView.findViewById(R.id.rv_recycler_view);
        readBundle(getArguments());
        recyclerView.setHasFixedSize(true);
        adapter = new SearchAdapter(docsList);
        recyclerView.setAdapter(adapter);
        loadJSON();
        return rootView;
    }

    private void readBundle(Bundle args) {
        Log.d(TAG, "reading bundle args: " + args);
        if (args != null) {
            position = args.getInt("position");
            Log.d(TAG, "userQueryList: (should be null) " + userQueryList);//should be empty
            userQueryList = args.getStringArrayList("userQueryList");
            Log.d(TAG, "userQueryList: (should have query and section values) " + userQueryList);
        }
    }

    private void loadJSON() {
        Log.d(TAG, "loading JSON");
        Gson gson = new GsonBuilder().serializeNulls().create();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
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
        String query;
        query = userQueryList.get(0);
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
                if (!response.isSuccessful()) {
                    textViewResult.setText(response.code());
                    Thread.currentThread().getStackTrace();
                    return;
                }
                if (response.body() != null) {
                    if (response.body().getDocsResponse() != null) {
                        docsList.addAll(response.body().getDocsResponse().getDocsList());
                        //getCount() & onBindViewHolder() called next in MyAdapter
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.d(TAG, "docsList:" + response.body().getDocsResponse());
                    }
                } else {
                    Log.d(TAG, "response.body(): " + response.body());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Handling Bundle Restoration
        Icepick.restoreInstanceState(this, savedInstanceState);
        // Update Design (Developer will call this method instead of override onActivityCreated())
        this.updateDesign();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Handling Bundle Save
        Icepick.saveInstanceState(this, outState);
    }

    protected SearchFragment newInstance() {
        return new SearchFragment();
    }

    protected int getFragmentLayout() {
        return R.layout.fragment_detail;
    }

    protected void configureDesign() {
    }

    protected void updateDesign() {
    }
}