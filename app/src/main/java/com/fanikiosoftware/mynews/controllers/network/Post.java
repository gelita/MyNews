package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {

    // from "results" on API call
    String section;
    String subsection;
    String title;
    String url;
    @SerializedName("created_date")
    String date;
    @SerializedName("multimedia")
    List<Multimedia> multimediaList;


    public String getSection() {
        return section;
    }

    public String getSubsection() {
        return subsection;
    }

    public List<Multimedia> getMultimediaList() {
        return multimediaList;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
