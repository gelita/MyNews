package com.fanikiosoftware.mynews.controllers.network;

import com.google.gson.annotations.SerializedName;

public class SearchResponse {

    @SerializedName("response")
    private DocsResponse docsResponse;

    public DocsResponse getDocsResponse() {
        return docsResponse;
    }
}
