package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.frameupclient.Model.Poll;
import com.example.frameupclient.Model.PollAPI;
import com.example.frameupclient.Model.PollAdapter;
import com.example.frameupclient.Model.Society;
import com.example.frameupclient.Model.SocietyAPI;
import com.example.frameupclient.Model.SocietyAdapter;
import com.example.frameupclient.Model.SocietyOperative;
import com.example.frameupclient.Model.SocietyOperativeAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewSociety extends AppCompatActivity implements SocietyRecyclerViewInterface {

    String rollNo;
    List<Society> s;
    RecyclerView RS;
    Button profile_btn,society_btn,home_btn,req_button;
    ConstraintLayout cs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_society);
        RS =findViewById(R.id.RS_recycler_view);
        RS.setLayoutManager(new LinearLayoutManager(this));

        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));

        profile_btn =findViewById(R.id.profile_button_vs);
        society_btn =findViewById(R.id.society_button_vs);
        home_btn =findViewById(R.id.home_button_vs);
        req_button =findViewById(R.id.req_button_vs);
        cs= findViewById(R.id.view_society_navbaar);




        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo = extras.getString("rollNo");
        }

        if(rollNo.compareTo("admin")==0){

            cs.setVisibility(View.INVISIBLE);
        }



        req_button.setOnClickListener(view->{
            Intent intent1 = new Intent(this, RequestList.class);
            Intent intent2 = new Intent(this, Notification.class);
            intent1.putExtra("rollNo", rollNo);
            intent2.putExtra("rollNo", rollNo);
            RetrofitService retrofitService = new RetrofitService();
            SocietyAPI societyAPI =  retrofitService.getRetrofit().create(SocietyAPI.class);
            societyAPI.isHeadAnyone(rollNo).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if(Integer.valueOf(response.body())>0){
                        startActivity(intent1);
                    }else{
                        startActivity(intent2);
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    startActivity(intent2);
                }
            });

        });



        society_btn.setBackgroundTintList(this.getColorStateList((R.color.Primary_Color_2)));



        profile_btn.setOnClickListener(view->{
            Intent intent = new Intent(this, UserProfile.class);
            intent.putExtra("rollNo", rollNo);
            startActivity(intent);

        });

        home_btn.setOnClickListener(view->{
            Intent intent = new Intent(this, VisitorHome.class);
            intent.putExtra("rollNo", rollNo);
            startActivity(intent);

        });
        

        loadSocieties();
    }

    private void loadSocieties() {
        RetrofitService retrofitService = new RetrofitService();
        SocietyAPI societyAPI = retrofitService.getRetrofit().create(SocietyAPI.class);

        societyAPI.getAllSociety().enqueue(new Callback<List<Society>>() {
            @Override
            public void onResponse(Call<List<Society>> call, Response<List<Society>> response) {
                populateSocietyList(response.body());
                s=response.body();
            }

            @Override
            public void onFailure(Call<List<Society>> call, Throwable t) {

            }
        });

    }

    private void populateSocietyList(List<Society> body) {
        SocietyAdapter societyAdapter =new SocietyAdapter(body, this);
        RS.setAdapter(societyAdapter);
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, SocietyPage.class);
        Society society = s.get(position);
        intent.putExtra("societyId",society.getSocietyId());
        intent.putExtra("rollNo", rollNo);
        startActivity(intent);
    }
}