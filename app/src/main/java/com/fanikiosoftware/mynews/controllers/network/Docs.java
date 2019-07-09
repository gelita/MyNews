package com.fanikiosoftware.mynews.controllers.network;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Docs {

    //ARTICLE SEARCH API
    @SerializedName("section_name")
    String searchSection;
    @SerializedName("subsection_name")
    String searchSubsection;
    //url for the article
    @SerializedName("web_url")
    String searchUrl;
    @SerializedName("pub_date")
    String searchDate;

    @Nullable
    @SerializedName("multimedia")
    List<Multimedia> multimediaList;


    public List<Multimedia> getMultimediaList() {
        return multimediaList;
    }

    public String getSearchSection() {
        return searchSection;
    }

    public String getSearchSubsection() {
        return searchSubsection;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public String getSearchDate() {
        return searchDate;
    }


}
