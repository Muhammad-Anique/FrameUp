package com.example.frameupclient.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface VisitorAPI {
    @GET("/visitor/get-all")
    Call<List<Visitor>> getAllVisitors();

    @POST("/visitor/save")
    Call<Visitor> save(@Body Visitor visitor);

    @GET("/person/{roll}")
    Call<Person> getPersonByRollNo(@Path("roll") String roll);

    @GET("/visitor/{roll}")
    Call<Visitor> getVisitorByRollNo(@Path("roll") String roll);

    @GET("/visitor/otp/{roll}")
    Call<String> getOTPByRollNo(@Path("roll") String roll);

    @PUT("/visitor/{roll}/update/status")
    Call<Visitor> updateStatus(@Path("roll") String roll);

    @PUT("/visitor/{roll}/update/pic")
    Call<Visitor> updateProfilePic(@Path("roll") String roll, @Body String url);

    @PUT("/visitor/status/update/{roll}/{st}")
    Call<Visitor> updateStatusByRoll(@Path("roll") String roll,@Path("st") String st);


}
