package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostResponse {

    @SerializedName("results")
    private List<Post> resultsList;

    @SerializedName("docs")
    private List<Post> docsList;

    @SerializedName("num_results")
    private int numResults;

    public List<Post> getResultsList() {
        return resultsList;
    }

    public int getNumResults() {
        return numResults;
    }

    public List<Post> getDocsList() {
        return docsList;
    }
}