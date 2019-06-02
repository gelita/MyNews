package com.fanikiosoftware.mynews.controllers.network;

public class Post {

    private String section;
    private String title;
    private String imageUrl;


    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
