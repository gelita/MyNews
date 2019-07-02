package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Docs {

    // from "results" on API call
    @SerializedName("web_url")
    String url;

    @SerializedName("multimedia")
    List<Multimedia> multimediaList;

    @SerializedName("pub_date")
    String date;
    @SerializedName("section_name")
    String section;
    @SerializedName("subsection_name")
    String subsection;

    public String getUrl() {
        return url;
    }

    public List<Multimedia> getMultimediaList() {
        return multimediaList;
    }

    public String getDate() {
        return date;
    }

    public String getSection() {
        return section;
    }

    public String getSubsection() {
        return subsection;
    }
}
