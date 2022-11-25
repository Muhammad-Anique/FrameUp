package com.example.frameupclient.Model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PollRespondedAPI {

    @GET("/poll-responded/save")
    Call<PollResponded> save(@Body PollResponded pollResponded);

    @GET("/poll-responded/{roll}")
    Call<PollResponded> getResponseExistence(@Path("roll") String roll);
}
