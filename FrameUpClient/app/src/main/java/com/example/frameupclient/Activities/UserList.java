package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.frameupclient.Model.Post;
import com.example.frameupclient.Model.PostAPI;
import com.example.frameupclient.Model.RvAdapter;
import com.example.frameupclient.Model.SocietyParticipationAPI;
import com.example.frameupclient.Model.UserListAdapter;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserList extends AppCompatActivity {

    RecyclerView Rul;
    int societyID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            societyID = extras.getInt("societyId");//The key argument here must match that used in the other activity
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));

        Rul = findViewById(R.id.Recyc_user_list);
        Rul.setLayoutManager(new LinearLayoutManager(this));

        System.out.println(";;;;44;;;");
        System.out.println(societyID);
        System.out.println(";;;;44;;;");
        loadUsers();
    }

    private void loadUsers() {
        RetrofitService retrofitService = new RetrofitService();
        SocietyParticipationAPI societyParticipationAPI = retrofitService.getRetrofit().create(SocietyParticipationAPI.class);
        societyParticipationAPI.getMemberBySId(societyID).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                System.out.println(response.body());
                populate(response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });

    }

    public void populate(List<String> s){

        UserListAdapter userListAdapter = new UserListAdapter(s,1,societyID);
        Rul.setAdapter(userListAdapter);
    }
}