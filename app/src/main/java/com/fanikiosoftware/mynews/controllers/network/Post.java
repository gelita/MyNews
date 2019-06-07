package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

public class Post {

    String section;
    String subsection;
    String title;
    String url;
    @SerializedName("created_date")
    String date;

    public String getSection() {
        return section;
    }
    public String getSubsection() {
        return subsection;
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
