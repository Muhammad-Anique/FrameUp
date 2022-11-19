package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frameupclient.Model.ReportAPI;
import com.example.frameupclient.Retrofit.RetrofitService;


public class ViewReport extends AppCompatActivity {
    public void getCount()
    {
        RetrofitService retrofitService=new RetrofitService();
        ReportAPI reportAPI=retrofitService.getRetrofit().create(ReportAPI.class);

    }



}

