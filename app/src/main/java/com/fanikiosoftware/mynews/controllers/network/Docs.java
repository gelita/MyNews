package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Docs {

    // from "results" on API call
    String subsection_name;
    String getSubsection_name;
    String pub_date;
    String headline;
    @SerializedName("web_url")
    String url;
    @SerializedName("created_date")
    String date;
    @SerializedName("multimedia")
    List<Multimedia> multimediaList;
}
