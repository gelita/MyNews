package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Post {

    private int id;
    private String title;
    private Date date;

    @SerializedName("body")
    private String text;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
