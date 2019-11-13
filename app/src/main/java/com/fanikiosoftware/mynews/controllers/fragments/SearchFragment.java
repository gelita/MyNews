package com.fanikiosoftware.mynews.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import icepick.Icepick;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    private TextView textViewResult;
    private SearchAdapter adapter;
    private String startDate;
    private String endDate;
    private ArrayList<String> userQueryList;
    private final List<Docs> docsList = new ArrayList<>();

    //fragment constructor
    public static SearchFragment newInstance(
            int position, ArrayList<String> userQueryList, String start, String end) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putStringArrayList(Constants.USER_QUERY_LIST, userQueryList);
        bundle.putString(Constants.DATE_START, start);
        bundle.putString(Constants.DATE_END, end);
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        textViewResult = rootView.findViewById(R.id.tv_textView);
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_recycler_view);
        //method below
        readBundle(getArguments());
        recyclerView.setHasFixedSize(true);
        adapter = new SearchAdapter(docsList);
        recyclerView.setAdapter(adapter);
        loadJSON();
        return rootView;
    }

    private void readBundle(Bundle args) {
        if (args != null) {
            int position = args.getInt("position");
            startDate = args.getString(Constants.DATE_START);
            endDate = args.getString(Constants.DATE_END);
            userQueryList = args.getStringArrayList(Constants.USER_QUERY_LIST);
        }
    }

    private void loadJSON() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
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
        String query;
        query = userQueryList.get(0);
        StringBuilder section = new StringBuilder();
        if (userQueryList.size() > 1) {
            //get sections from list starting at 2nd item on list(1st item is user's query)
            for (int i = 1; i < userQueryList.size(); i++) {
                section.append(userQueryList.get(i)).append(",");
            }
        }
        Call<SearchResponse> call = newsApi.getDocs(query, section.toString(), startDate, endDate, Constants.API_KEY);
        assert call != null;
        //network call for Search
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(@NotNull Call<SearchResponse> call, @NotNull Response<SearchResponse> response) {
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
                    }
                }
            }

                @Override
                public void onFailure (@NotNull Call < SearchResponse > call, @NotNull Throwable t){
                    textViewResult.setText(t.getMessage());
                }
            });
        }

        public void onActivityCreated (Bundle savedInstanceState){
            super.onActivityCreated(savedInstanceState);
            // Handling Bundle Restoration
            Icepick.restoreInstanceState(this, savedInstanceState);
        }

        public void onSaveInstanceState (@NotNull Bundle outState){
            super.onSaveInstanceState(outState);
            //Handling Bundle Save
            Icepick.saveInstanceState(this, outState);
        }

        protected SearchFragment newInstance () {
            return new SearchFragment();
        }
    }