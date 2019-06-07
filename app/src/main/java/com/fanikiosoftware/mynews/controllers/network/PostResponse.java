package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostResponse {

    @SerializedName("num_results")
    private int numResults;
    @SerializedName("results")
    private List<Post> resultsList;

    public int getNumResults() {
        return numResults;
    }

    public List<Post> getResultsList() {
        return resultsList;
    }
}
