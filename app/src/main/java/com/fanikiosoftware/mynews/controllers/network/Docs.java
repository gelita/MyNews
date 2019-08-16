package com.fanikiosoftware.mynews.controllers.network;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Docs {

    //ARTICLE SEARCH API returns the following for each item in a List<Docs>
    @SerializedName("news_desk")
    private String searchSection;
    @SerializedName("subsection_name")
    private String searchSubsection;
    //url for the article
    @SerializedName("web_url")
    private String searchUrl;
    @SerializedName("pub_date")
    private String searchDate;
    @Nullable
    @SerializedName("multimedia")
    private List<Multimedia> multimediaList;
    @SerializedName("headline")
    private Headline headlineResponse;

    public Headline getHeadlineResponse() {
        return headlineResponse;
    }

    @org.jetbrains.annotations.Nullable
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
