package com.example.frameupclient.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SocietyOperativeAPI {

    @GET("/society-operative/{roll}")
    Call<SocietyOperative> getSocietyOperativeByRoll(@Path("roll") String roll);

    @POST("/society-operative/save")
    Call<SocietyOperative> save(@Body SocietyOperative societyOperative);

    @GET("/society-operative/{sid}/{type}")
    Call<List<SocietyOperative>> getSocietyOperativeByRollAndType(@Path("sid") int sid, @Path("type") int type);

    @GET("/society-operative/is-advisor/{type}")
    Call<Integer> isAdvisorByEmail(@Path("type") int type, @Body String email);

    @GET("/society-operative/advisors")
    Call<List<String>> getAdvisor();
}
