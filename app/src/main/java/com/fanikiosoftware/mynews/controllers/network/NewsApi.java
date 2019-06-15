package com.fanikiosoftware.mynews.controllers.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {

    //declare the method but since it's an INTERFACE do not make the method body
    // Retrofit will autogenerate the necessary code to get the data back

    //annotate w/ @Get("posts") to let retrofit know what is requested/required
    //will return a JSON array of posts
    @GET("home.json?api-key=nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6")
    Call<PostResponse> getPosts();

    @GET("business.json?api-key=nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6")
    Call<PostResponse> getPosts1();

    @GET("technology.json?api-key=nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6")
    Call<PostResponse> getPosts2();

    @GET("fashion.json?api-key=nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6")
    Call<PostResponse> getPosts3();

    @GET("health.json?api-key=nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6")
    Call<PostResponse> getPosts4();

    @GET("realestate.json?api-key=nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6")
    Call<PostResponse> getPosts5();
}
