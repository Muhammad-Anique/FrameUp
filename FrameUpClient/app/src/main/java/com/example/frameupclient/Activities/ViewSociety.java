package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.frameupclient.adapter.SocietyAdapter;
import com.example.frameupclient.Model.Society;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.example.frameupclient.Retrofit.SocietyAPI;
import com.example.frameupclient.adapter.SocietyHolder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewSociety extends AppCompatActivity implements SocietyHolder.OnClickListenerSociety {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


        recyclerView = findViewById(R.id.societylist_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadSociety();
    }

        private void loadSociety(){
            RetrofitService retrofitService = new RetrofitService();
            SocietyAPI societyAPI = retrofitService.getRetrofit().create(SocietyAPI.class);
            societyAPI.getAllSociety().enqueue(new Callback<List<Society>>() {
                @Override
                public void onResponse(Call<List<Society>> call, Response<List<Society>> response) {
                 populateSocietyView(response.body());
                }

                @Override
                public void onFailure(Call<List<Society>> call, Throwable t) {
                    Toast.makeText(ViewSociety.this, "Fail to Load", Toast.LENGTH_SHORT).show();
                }
            });

    }
    private void populateSocietyView(List<Society> societyList) {
        SocietyAdapter societyAdapter = new SocietyAdapter(societyList, this);
        recyclerView.setAdapter(societyAdapter);
    }

    @Override
    public void OnSocietyClick(int position) {
        Intent intent=new Intent(this,OnClickViewSociety.class);
        startActivity(intent);
    }
}