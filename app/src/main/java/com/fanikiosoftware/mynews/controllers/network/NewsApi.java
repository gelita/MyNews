package com.fanikiosoftware.mynews.controllers.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {

    //declare the method but since it's an INTERFACE do not make the method body
    // Retrofit will autogenerate the necessary code to get the data back

    //annotate w/ @Get("posts") to let retrofit know what is requested/required
    //will return a JSON array of posts
    @GET("topstories/v2/home.json?api-key=nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6")
    Call<PostResponse> getPosts();

    @GET("topstories/v2/business.json?api-key=nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6")
    Call<PostResponse> getPosts1();

    @GET("topstories/v2/technology.json?api-key=nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6")
    Call<PostResponse> getPosts2();

    @GET("topstories/v2/fashion.json?api-key=nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6")
    Call<PostResponse> getPosts3();

    @GET("topstories/v2/realestate.json?api-key=nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6")
    Call<PostResponse> getPosts4();

    //article search query1 using user input search term
    @GET("?api-key=nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6")
    Call<PostResponse> getPosts5();
}
