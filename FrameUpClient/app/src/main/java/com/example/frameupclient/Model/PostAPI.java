package com.example.frameupclient.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostAPI {

    @GET("/post/get-all")
    Call<List<Post>> getAllPosts();

    @POST("/post/save")
    Call<Post> save(@Body Post post);

    @DELETE("/post/delete/{id}")
    Call<String> deletePost(@Path("id") int id);
}
