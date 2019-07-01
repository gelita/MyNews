package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search {

    // from "responses" on API call
    @SerializedName("docs")
    List<Docs> searchResults;

}
