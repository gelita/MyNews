package com.fanikiosoftware.mynews.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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

import java.util.List;

import icepick.Icepick;
import icepick.State;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TechFragment extends Fragment {

    String BASE_URL = "https://api.nytimes.com/svc/topstories/v2/";
    public TextView textViewResult;
    MyAdapter adapter;
    NewsApi newsApi;
    @State
    int buttonTag;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        textViewResult = rootView.findViewById(R.id.tv_textView);
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
        return rootView;
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsApi = retrofit.create(NewsApi.class);
        Call<PostResponse> call = newsApi.getPosts();
        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if(!response.isSuccessful()){
                    //get the error message
                    textViewResult.setText("CODE: " + response.code());
                    return;
                }
                PostResponse postResponse = response.body();
                //get the array from the PostResponse object
                List<Post> postList = postResponse.getResultsList();
                for(Post post : postList){



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
    protected Fragment newInstance() {
        return new PopularFragment();
    }

    protected int getFragmentLayout() {
        return R.layout.fragment_detail;
    }

    protected void configureDesign() {
    }

    protected void updateDesign() {
        //  this.updateTextView(this.buttonTag);
    }
}