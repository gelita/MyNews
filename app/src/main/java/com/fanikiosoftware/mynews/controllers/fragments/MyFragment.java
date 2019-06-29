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
import com.fanikiosoftware.mynews.controllers.activities.MyAdapter;
import com.fanikiosoftware.mynews.controllers.network.NewsApi;
import com.fanikiosoftware.mynews.controllers.network.Post;
import com.fanikiosoftware.mynews.controllers.network.PostResponse;

import java.util.ArrayList;
import java.util.List;

import icepick.Icepick;
import icepick.State;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyFragment extends Fragment {

    private static final String BASE_URL = "https://api.nytimes.com/svc/";
    public static final String API_KEY = "nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6";
    public TextView textViewResult;
    MyAdapter adapter;
    NewsApi newsApi;
    @State
    int position;
    String userQuery;
    RecyclerView recyclerView;
    List<Post> postList = new ArrayList<>();
    public static final String TAG = "MyFragment";

    public static MyFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static MyFragment newInstance(int position, String userQuery) {
        Log.d(TAG, "MyFragment newInstance 2 starting");
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putString("userQuery", userQuery);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, " :: MyFragment onCreateView called");
        //Get activity_query identifier from abstract method declared in child class
        //this method will report the correct activity_query's identifier so the correct activity_query will be used
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        textViewResult = rootView.findViewById(R.id.tv_textView);
        recyclerView = rootView.findViewById(R.id.rv_recycler_view);
        readBundle(getArguments());
        recyclerView.setHasFixedSize(true);
        adapter = new MyAdapter(postList);
        recyclerView.setAdapter(adapter);
        loadJSON(position);
        return rootView;
    }

    private void readBundle(Bundle args) {
        if (args != null) {
            position = args.getInt("position");
            if (position > 5) {
                userQuery = args.getString("userQuery");
                System.out.println("query: " + userQuery + " Key: " + API_KEY);
            }
        }
    }

    private void loadJSON(int whichFrag) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //retrofit will create the body of the method being called w/out a defn in NewsApi.class
        newsApi = retrofit.create(NewsApi.class);
        Call<PostResponse> call = null;
        System.out.println("whichFrag: " + whichFrag);
        if (whichFrag == 0) {
            call = newsApi.getPosts();
        } else if (whichFrag == 1) {
            call = newsApi.getPosts1();
        } else if (whichFrag == 2) {
            call = newsApi.getPosts2();
        } else if (whichFrag == 3) {
            call = newsApi.getPosts3();
        } else if (whichFrag == 4) {
            call = newsApi.getPosts4();
        } else if (whichFrag == 5) {
            call = newsApi.getPosts5();
        } else if (whichFrag == 6) {
            //remove ending "," in query string
            StringBuilder builder = new StringBuilder(userQuery);
            builder.deleteCharAt(userQuery.length() - 1);
            String[] query = userQuery.split("$");
            call = newsApi.getPosts6(query[0], query[1], API_KEY);
        }
        assert call != null;
        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Error Code line MyFragment line 120: " + response.code());
                    Thread.currentThread().getStackTrace();
                    return;
                }
                if(postList != null) {
                    Log.d(TAG, "postList not null");
                    if(response.body().getResultsList() != null) {
                        Log.d(TAG, "resultsList not null");
                        postList.addAll(response.body().getResultsList());
                        //getCount() & onBindViewHolder() called next in MyAdapter
                        adapter.notifyDataSetChanged();
                    }else {
                        Log.d(TAG, "resultsList NULL");
                    }
                }else{
                    Log.d(TAG, "postList NULL");
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
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

    protected MyFragment newInstance() {
        return new MyFragment();
    }

    protected int getFragmentLayout() {
        return R.layout.fragment_detail;
    }

    protected void configureDesign() {
    }

    protected void updateDesign() {
    }
}