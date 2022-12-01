package com.example.frameupclient.Retrofit;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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

        OkHttpClient innerClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES) // read timeout
                .build();

        retrofit = new Retrofit.Builder()
          .baseUrl("http://192.168.10.12:8080")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(innerClient)
                .build();
    }

    public Retrofit getRetrofit()
    {
        return retrofit;
    }
}
