package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {

    String section;
    String subsection;
    String title;
    String url;
    @SerializedName("created_date")
    String date;
    @SerializedName("multimedia")
    List<ImageUrl> imageUrlList;


    public String getSection() {
        return section;
    }

    public String getSubsection() {
        return subsection;
    }

    public List<ImageUrl> getImageUrlList() {
        return imageUrlList;
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


    //**********  MULTIMEDIA INNER CLASS *************//

    public class ImageUrl {

        @SerializedName("url")
        String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }
    }
}
