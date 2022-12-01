package com.example.frameupclient.Model;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RequestAPI {

    @GET("/requests/{roll}")
    Call<List<Request>> getAllRequestsByRoll(@Path("roll") String roll);

    @POST("/requests/save")
    Call<Request> save(@Body Request request);

    @DELETE("/requests/delete/{reqId}")
    Call<Void> deleteReq(@Path("reqId") int reqId);

    @GET("/request/{roll}/{type}/{sid}")
    Call<Integer> getRequestTypeByRoll( @Path("roll") String roll,@Path("type") String type, @Path("sid") int sid);

}
