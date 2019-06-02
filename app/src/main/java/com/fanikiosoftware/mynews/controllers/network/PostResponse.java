package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostResponse {

    @SerializedName("num_results")
    private int numResults;
    private String section;
    @SerializedName("last_updated")
    private String lastUpdated;
    @SerializedName("results")
    private List<Post> resultsList;

    public int getNumResults() {
        return numResults;
    }

    public String getSection() {
        return section;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public List<Post> getResultsList() {
        return resultsList;
    }
}
