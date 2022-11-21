package com.example.frameupclient.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PollAPI {

    @GET("/poll/get-all")
    Call<List<Poll>> getAllPoll();

    @POST("/poll/save")
    Call<Poll> save(@Body Poll poll);
}
