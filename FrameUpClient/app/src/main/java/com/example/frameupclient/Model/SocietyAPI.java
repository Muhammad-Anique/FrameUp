package com.example.frameupclient.Retrofit;

import com.example.frameupclient.Model.Society;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface SocietyAPI{

    @GET("/society/get-all")
    Call<List<Society>> getAllSociety();

    @POST("/society/save")
    Call<Society> save(@Body Society society);

    @GET("/Society/get_societyName")
    Call<Society>getSocietyNameList();
}
