package com.example.frameupclient.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface PollAPI {

    @GET("/poll/get-all")
    Call<List<Poll>> getAllPoll();

    @POST("/poll/save")
    Call<Poll> save(@Body Poll poll);

    @GET("/poll/{poll}")
    Call<Poll> getPollById(@Path("poll") int poll);

    @PUT("/poll/update/{pollId}")
    Call<Poll> addPollResponse(@Path("pollId") int pollId, @Body Poll poll);


    @DELETE("/poll/delete/{id}")
    Call<String> deletePoll(@Path("id") int id);
}
