package com.example.frameupclient.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SocietyParticipationAPI {
    @GET("/society-participation/get-all")
    Call<List<SocietyParticipation>> getAllSocietyParticipation();

    @POST("/society-participation/save")
    Call<SocietyParticipation> save(@Body SocietyParticipation societyParticipation);

    @GET("/society-participation/get-all/count/{sid}")
    Call<Integer> getMemberCountById(@Path("sid") int sid);

    @GET("/society-participation/get-all/rolls/{sid}")
    Call<List<String>> getMemberBySId(@Path("sid") int sid);

}
