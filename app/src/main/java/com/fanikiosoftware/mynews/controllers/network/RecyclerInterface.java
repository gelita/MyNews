package com.fanikiosoftware.mynews.controllers.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecyclerInterface {

    String BASE_URL = "https://api.nytimes.com/svc/topstories/v2/";

    @GET("home")//home.json?api-key=nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6
    Call<String> getString();
}
