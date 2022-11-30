package com.example.frameupclient.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET("/society-participation/{roll}")
    Call<List<SocietyParticipation>> getMemberExistence(@Path("roll") String roll);

    @GET("/society-participation/rating/{sid}")
    Call<Float> getRatingBySID(@Path("sid") int sid);

    @PUT("/society-participation/update/rating/{sid}/{roll}")
    Call<Society> updateRating(@Path("sid") int sid, @Path("roll") String roll,@Body SocietyParticipation societyParticipation);


    @GET("/society-participation/get-exist/{sid}/{roll}")
    Call<Integer> getSocietyPByRollAndSid(@Path("sid") int sid, @Path("roll") String roll) ;

    @DELETE("/society-participation/delete/{sid}/{roll}")
    Call<String> deleteParticipation(@Path("sid") int sid, @Path("roll") String roll) ;
}
