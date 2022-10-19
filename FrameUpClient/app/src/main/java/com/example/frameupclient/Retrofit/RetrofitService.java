package com.example.frameupclient.Retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private Retrofit retrofit;

    public RetrofitService()
    {
        initializeRetrofit();
        
    }

    //IP ADDRESS IS DYNAMIC, IT MUST BE UPDATED EVERYTIME

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
          .baseUrl("http://192.168.10.4:8080")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit()
    {
        return retrofit;
    }
}
