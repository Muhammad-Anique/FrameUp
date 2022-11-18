package com.example.frameupclient.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReportAPI {

    @GET("/report/get-all")
    Call<List<Report>> getAllReport();

    @POST("/report/save")
    Call<Report> save(@Body Report report);

    @GET("/report/{societyName")
    Call<Report> countSocietyMembers(@Path("societyName")String societyName);


    @GET("/person/{roll}")
    Call<Person> getPersonByRollNo(@Path("roll") String roll);
}
