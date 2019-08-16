package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

public class Headline {

    @SerializedName("main")
    private String title;

    public String getTitle() {
        return title;
    }
}
