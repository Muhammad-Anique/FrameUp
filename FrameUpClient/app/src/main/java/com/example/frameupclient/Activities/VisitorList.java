package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.frameupclient.Model.SocietyOperativeAPI;
import com.example.frameupclient.Model.SocietyParticipationAPI;
import com.example.frameupclient.Model.UserListAdapter;
import com.example.frameupclient.Model.Visitor;
import com.example.frameupclient.Model.VisitorAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitorList extends AppCompatActivity {

    RecyclerView Rul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_list);
        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        Rul = findViewById(R.id.rerere);
        Rul.setLayoutManager(new LinearLayoutManager(this));
        loadAdvisors();
    }

    private void loadAdvisors() {
        RetrofitService retrofitService = new RetrofitService();
        SocietyOperativeAPI societyOperativeAPI = retrofitService.getRetrofit().create(SocietyOperativeAPI.class);
        societyOperativeAPI.getAdvisor().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.body()!=null) {
                    populate(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });

    }

    public void populate(List<String> s){

        UserListAdapter userListAdapter = new UserListAdapter(s,2, 999);
        Rul.setAdapter(userListAdapter);
    }
}
