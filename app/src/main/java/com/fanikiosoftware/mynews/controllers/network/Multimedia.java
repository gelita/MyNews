package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

public class Multimedia {

    String url; //image url for articles

    @SerializedName("url")
    String imageURL;

    public String getUrl() {
        return url;
    }

    public String getImageURL() {
        return imageURL;
    }
}