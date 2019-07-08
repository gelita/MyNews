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
import com.fanikiosoftware.mynews.controllers.network.PostResponse;
import com.fanikiosoftware.mynews.controllers.utility.Constants;

import java.util.ArrayList;
import java.util.List;

import icepick.Icepick;
import icepick.State;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    public TextView textViewResult;
    MyAdapter adapter;
    NewsApi newsApi;
    @State
    int position;
    ArrayList<String> userQueryList;
    RecyclerView recyclerView;
    List<Docs> docsList = new ArrayList<>();
    public static final String TAG = "SearchFragment";

    public static SearchFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static SearchFragment newInstance(int position, ArrayList<String> userQueryList) {
        Log.d(TAG, "MyFragment newInstance 2 starting");
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
        adapter = new MyAdapter(docsList);
        recyclerView.setAdapter(adapter);
        loadJSON(position);
        return rootView;
    }

    private void readBundle(Bundle args) {
        Log.d(TAG, " :: MyFragment reading bundle arg: " + args);
        if (args != null) {
            position = args.getInt("position");
            Log.d(TAG, "pos: " + position);
            if (position > 5) {
                Log.d(TAG, "userQueryList: (should be null) " + userQueryList);//should be empty
                userQueryList = args.getStringArrayList("userQueryList");
                Log.d(TAG, "userQueryList: (should have query and section values) " + userQueryList);
            }
        }
    }

    private void loadJSON(int whichFrag) {
        Log.d(TAG, ": loading JSON");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //retrofit will create the body of the method being called w/out a defn in NewsApi.class
        newsApi = retrofit.create(NewsApi.class);
        Log.d(TAG, "whichFrag == 6");
        //get the user query that was in the arguments of the bundle
        String query = userQueryList.get(0);
        String section = "";
        Log.d(TAG, "userQueryList.size() = " + String.valueOf(userQueryList.size()));
        if (userQueryList.size() > 1) {
            //get sections from list starting at 2nd item on list(1st item is user's query)
            for (int i = 1; i < userQueryList.size(); i++) {
                section += userQueryList.get(i) + ",";
            }
        }
        Log.d(TAG, "query = " + query + ", section = " + section + "api-key: " + Constants.API_KEY);
        Call<PostResponse> call = newsApi.getPosts6(query, section, Constants.API_KEY);
        assert call != null;
        Log.d(TAG, "starting network call");
        if (position < 5) {
            call.enqueue(new Callback<PostResponse>() {

                @Override
                public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                    if (!response.isSuccessful()) {
                        textViewResult.setText("Error Code line MyFragment line 120: " + response.code());
                        Thread.currentThread().getStackTrace();
                        return;
                    }
                    Log.d(TAG, "response body: " + response.body() + " resultsList: " + response.body().getResultsList());
                    if (response.body() != null && response.body().getResultsList() != null) {
                        docsList.addAll(response.body().getResultsList());
                        //getCount() & onBindViewHolder() called next in MyAdapter
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.d(TAG, "resultsList NULL");
                        return;
                    }
                }

                @Override
                public void onFailure(Call<PostResponse> call, Throwable t) {
                    textViewResult.setText(t.getMessage());
                }
            });
        } else {
            //network call for Search api --> position > 5
            call.enqueue(new Callback<PostResponse>() {
                @Override
                public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                    if (!response.isSuccessful()) {
                        textViewResult.setText("Error Code line MyFragment line 120: " + response.code());
                        Thread.currentThread().getStackTrace();
                        return;
                    }
                    Log.d(TAG, "response body: " + response.body() + " docsList: " + response.body().getDocsList());
                    if (response.body() != null && response.body().getDocsList() != null) {
                        docsList.addAll(response.body().getDocsList());
                        //getCount() & onBindViewHolder() called next in MyAdapter
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.d(TAG, "docsList NULL");
                        return;
                    }
                }

                @Override
                public void onFailure(Call<PostResponse> call, Throwable t) {
                    textViewResult.setText(t.getMessage());
                }
            });
        }
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