package com.fanikiosoftware.mynews.controllers.network;

import java.util.List;

import retrofit2.Call;

public interface JsonPlaceHolderApi {

    //declare the method but since it's an INTERFACE do not make the method body
    // Retrofit will autogenerate the necessary code to get the data back

    //annotate with get posts so that @Get("posts") to let retrofit know what is requested/required
    //will return a Json array of posts
    Call<List<Post>> getPosts();
}
