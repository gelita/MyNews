package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("hits")
    int numHits;

    public int getNumHits() {
        return numHits;
    }
}
