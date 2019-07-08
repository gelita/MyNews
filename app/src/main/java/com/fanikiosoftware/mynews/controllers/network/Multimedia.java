package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

public class Multimedia {

    String url; //image url for articles

    Headline headline;

    public String getUrl() {
        return url;
    }

    public Headline getHeadline() {
        return headline;
    }
    //--------------Headline inner class -----------------//

    public class Headline {

        @SerializedName("print_headline")
        private String title;

        public String getTitle() {
            return title;
        }
    }
}