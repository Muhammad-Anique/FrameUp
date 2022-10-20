package com.example.frameupclient.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VisitorAPI {
    @GET("/visitor/get-all")
    Call<List<Visitor>> getAllVisitors();

    @POST("/visitor/save")
    Call<Visitor> save(@Body Visitor visitor);
}