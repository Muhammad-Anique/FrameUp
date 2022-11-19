package com.example.frameupclient.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostAPI {

    @GET("/post/get-all")
    Call<List<Post>> getAllPosts();

    @POST("/post/save")
    Call<Post> save(@Body Post post);
}
