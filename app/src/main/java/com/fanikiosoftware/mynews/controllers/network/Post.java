package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

public class Post {

    String section;
    String title;
    @SerializedName("created_date")
    String date;

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return dateeeeeee;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
