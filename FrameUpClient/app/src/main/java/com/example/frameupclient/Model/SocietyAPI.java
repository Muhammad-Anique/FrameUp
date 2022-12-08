package com.example.frameupclient.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SocietyAPI {
    @GET("/society/get-all")
    Call<List<Society>> getAllSociety();

    @POST("/society/save")
    Call<Society> save(@Body Society society);

    @GET("/society/{sid}")
    Call<Society> getSocietyById(@Path("sid") int sid);

    @GET("/society/head/{sName}")
    Call<Society> getSocietyByName(@Path("sName") String sName);

    @DELETE("/society/delete/{sid}")
    Call<String> deleteSocietyById(@Path("sid") int sid);

    @GET("/society/whole/delete/{sid}")
    Call<String> deleteMembersAndOperatives(@Path("sid") int sid);

    @GET("/society/who-is-this/{sid}/{roll}")
    Call<Integer> whoIsThis(@Path("sid") int sid, @Path("roll") String roll);

    @GET("/society/is-head/{rollNo}/{sid}")
    Call<Integer> isHead(@Path("rollNo") String rollNo, @Path("sid") int sid);


    @GET("/society/is-head/{rollNo}")
    Call<Integer> isHeadAnyone(@Path("rollNo") String rollNo);


}
