package com.fanikiosoftware.mynews.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanikiosoftware.mynews.R;
import com.fanikiosoftware.mynews.controllers.activities.MyAdapter;
import com.fanikiosoftware.mynews.controllers.network.NewsApi;
import com.fanikiosoftware.mynews.controllers.network.Post;
import com.fanikiosoftware.mynews.controllers.network.PostResponse;
import com.fanikiosoftware.mynews.controllers.utility.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

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

    private TextView textViewResult;
    private MyAdapter adapter;
    @State
    int position;
    private final List<Post> postList = new ArrayList<>();
    private static final String TAG = "MyFragment";

    public static MyFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Get activity_query identifier from abstract method declared in child class
        //this method will report the correct activity_query's identifier so the correct activity_query will be used
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        textViewResult = rootView.findViewById(R.id.tv_textView);
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_recycler_view);
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
        }
    }

    private void loadJSON(int whichFrag) {
        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //retrofit will create the body of the method being called w/out a defn in NewsApi.class
        NewsApi newsApi = retrofit.create(NewsApi.class);
        Call<PostResponse> call = null;
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
        }
        assert call != null;
        call.enqueue(new Callback<PostResponse>() {

            @Override
            public void onResponse(@NotNull Call<PostResponse> call, @NotNull Response<PostResponse> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText(response.code());
                    Thread.currentThread().getStackTrace();
                    return;
                }
                if (response.body() != null && response.body().getResultsList() != null) {
                    postList.addAll(response.body().getResultsList());
                    //getCount() & onBindViewHolder() called next in MyAdapter
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NotNull Call<PostResponse> call, @NotNull Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Handling Bundle Restoration
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Handling Bundle Save
        Icepick.saveInstanceState(this, outState);
    }

    protected MyFragment newInstance() {
        return new MyFragment();
    }


}