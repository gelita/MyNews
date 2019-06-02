package com.fanikiosoftware.mynews.controllers.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {

    //declare the method but since it's an INTERFACE do not make the method body
    // Retrofit will autogenerate the necessary code to get the data back

    //annotate with get posts so that @Get("posts") to let retrofit know what is requested/required
    //will return a JSON array of posts
    @GET("home.json?api-key=nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6")
    Call<List<Post>> getPosts();
}
