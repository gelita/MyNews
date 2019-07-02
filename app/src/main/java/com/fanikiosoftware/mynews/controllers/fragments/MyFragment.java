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
import com.fanikiosoftware.mynews.controllers.network.Docs;
import com.fanikiosoftware.mynews.controllers.network.NewsApi;
import com.fanikiosoftware.mynews.controllers.network.Post;
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

public class MyFragment extends Fragment {

    public TextView textViewResult;
    MyAdapter adapter;
    NewsApi newsApi;
    @State
    int position;
    ArrayList<String> userQueryList;
    public static final String API_KEY = "nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6";

    RecyclerView recyclerView;
    List<Post> postList = new ArrayList<>();
    List<Docs> docsList = new ArrayList<>();
    public static final String TAG = "MyFragment";

    public static MyFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static MyFragment newInstance(int position, ArrayList<String> userQueryList) {
        Log.d(TAG, "MyFragment newInstance 2 starting");
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putStringArrayList("userQueryList", userQueryList);
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
        } else if (whichFrag == 6) {
            Log.d(TAG, "whichFrag == 6");
            String query = userQueryList.get(0);
            String section = "";
            if(userQueryList.size() > 1) {
                //get sections from list starting at 2nd item on list(1st item is user's query)
                for (int i = 1; i < userQueryList.size(); i++) {
                    section += userQueryList.get(i) + ",";
                }
            }
            Log.d(TAG, "query = " + query + ", section = " + section);
            call = newsApi.getPosts6(query, section, API_KEY);
        }
        assert call != null;
        Log.d(TAG, "starting network call");
        if (position < 6) {
            call.enqueue(new Callback<PostResponse>() {
                @Override
                public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                    if (!response.isSuccessful()) {
                        textViewResult.setText("Error Code line MyFragment line 120: " + response.code());
                        Thread.currentThread().getStackTrace();
                        return;
                    }
                    if (response.body() != null && response.body().getResultsList() != null) {
                        postList.addAll(response.body().getResultsList());
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
            call.enqueue(new Callback<PostResponse>() {
                @Override
                public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                    if (!response.isSuccessful()) {
                        textViewResult.setText("Error Code line MyFragment line 120: " + response.code());
                        Thread.currentThread().getStackTrace();
                        return;
                    }
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