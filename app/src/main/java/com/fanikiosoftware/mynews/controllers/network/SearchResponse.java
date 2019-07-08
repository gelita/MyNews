package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

    @SerializedName("docs")
    private List<Docs> docsList;

    public List<Docs> getDocsList() {
        return docsList;
    }
}
