package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("hits")
    private
    int numHits;

    //returns the number of hits for a particular 'article search query'
    public int getNumHits() {
        return numHits;
    }
}
