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

    @GET("/report/members/{societyName}")
    long countSocietyMembers(@Path("societyName")String societyName);

    @GET("/report/male/{societyName}")
    long countMaleSocietyMembers(@Path("societyName")String societyName);

    @GET("/report/female/{societyName}")
    long countFemaleSocietyMembers(@Path("societyName")String societyName);

    @GET("/report/{batchNo}/{societyName}")
    Call<Report> getMembersByBatch(@Path("batchNo")int batchNumber,@Path("societyName") String societyName);

    @GET("/report/Male/Batch/{batchNo}/{societyName}/{gender}")
    int getMaleByBatch(@Path("batchNo")int batchNo, @Path("societyName") String societyName, @Path("gender")int gender);

   // @GET("/report/{societyName")
    //Call<Report> calculateAverageRating(@Path("societyName")String societyName);





    @GET("/person/{roll}")
    Call<Person> getPersonByRollNo(@Path("roll") String roll);
}
