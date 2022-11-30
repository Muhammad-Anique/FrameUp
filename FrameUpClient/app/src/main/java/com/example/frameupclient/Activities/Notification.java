package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.frameupclient.Model.NotificationAdapter;
import com.example.frameupclient.Model.Request;
import com.example.frameupclient.Model.RequestAPI;
import com.example.frameupclient.Model.RequestAdapter;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notification extends AppCompatActivity {

    RecyclerView Rn;
    String rollNo;
    Button noti_profile,noti_home,noti_soci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Rn=findViewById(R.id.notificationRecyc);
        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        Rn.setLayoutManager(new LinearLayoutManager(this));

        Button notification_btn;
        notification_btn=findViewById(R.id.noti_page_req_button);
        noti_home=findViewById(R.id.noti_page_home_btn);
        noti_profile=findViewById(R.id.noti_profile_button);
        noti_soci=findViewById(R.id.noti_page_society_button);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo = extras.getString("rollNo");
            System.out.println("Roll passes by home" + rollNo);
        }

        noti_soci.setOnClickListener(view->{
            Intent intent = new Intent(this, ViewSociety.class);
            intent.putExtra("rollNo",rollNo);
            startActivity(intent);
            finish();

        });

        noti_home.setOnClickListener(view->{
            Intent intent = new Intent(this, VisitorHome.class);
            intent.putExtra("rollNo",rollNo);
            startActivity(intent);
            finish();
        });

        noti_profile.setOnClickListener(view->{
            Intent intent = new Intent(this, UserProfile.class);
            intent.putExtra("rollNo",rollNo);
            startActivity(intent);
            finish();
        });

        notification_btn.setBackgroundTintList(this.getColorStateList((R.color.Primary_Color_2)));




        loadRequests();


    }

    private void loadRequests() {
        RetrofitService retrofitService = new RetrofitService();
        RequestAPI requestAPI = retrofitService.getRetrofit().create(RequestAPI.class);

        requestAPI.getAllRequestsByRoll(rollNo).enqueue(new Callback<List<Request>>() {
            @Override
            public void onResponse(Call<List<Request>> call, Response<List<Request>> response) {
                System.out.println("Request list => "+response.body());
                populateRequestList(response.body());
            }

            @Override
            public void onFailure(Call<List<Request>> call, Throwable t) {

            }
        });

    }

    private void populateRequestList(List<Request> body) {
        NotificationAdapter notificationAdapter =new NotificationAdapter(body);
        Rn.setAdapter(notificationAdapter);
    }


}