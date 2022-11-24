package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.frameupclient.Model.Society;
import com.example.frameupclient.Model.SocietyParticipation;
import com.example.frameupclient.Model.SocietyParticipationAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SocietyPage extends AppCompatActivity {

    Button  rate, joinUs, viewMember;
    CardView societyOperative, society_post;
    Dialog d;
    String rollNo;
    int demand;
    int sid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_page);
        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));

        societyOperative=findViewById(R.id.cardView_890);
        society_post=findViewById(R.id.PostCard);
        rate= findViewById(R.id.rateButton_sp);
        viewMember=findViewById(R.id.mem_view_sp_btn);
        joinUs=findViewById(R.id.button_join_society);
        d=new Dialog(this);
        d.setContentView(R.layout.success_member_dialogue);
        d.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo = extras.getString("userRoll");
            sid = extras.getInt("societyId");
        }

        joinUs.setOnClickListener(view->{
            RetrofitService retrofitService = new RetrofitService();
            SocietyParticipationAPI societyParticipationAPI = retrofitService.getRetrofit().create(SocietyParticipationAPI.class);
            SocietyParticipation sp = new SocietyParticipation();
            sp.setSocietyId(sid);
            sp.setRollNo(rollNo);
            sp.setParticipatedAs(1);
            sp.setRating(0);
            societyParticipationAPI.save(sp).enqueue(new Callback<SocietyParticipation>() {
                @Override
                public void onResponse(Call<SocietyParticipation> call, Response<SocietyParticipation> response) {
                    d.show();
                }

                @Override
                public void onFailure(Call<SocietyParticipation> call, Throwable t) {

                }
            });

        });

        viewMember.setOnClickListener(view->{
            Intent intent = new Intent(this,UserList.class);
            demand = 1;
            intent.putExtra("demand",demand);
            intent.putExtra("societyId",sid);
            intent.putExtra("userRoll",rollNo);
            startActivity(intent);

        });

        societyOperative.setOnClickListener(view->{
            Intent intent = new Intent(this, OperativeSociety.class);
            demand = 2;
            intent.putExtra("demand",demand);
            intent.putExtra("societyId",sid);
            intent.putExtra("userRoll",rollNo);
            startActivity(intent);

        });

        rate.setOnClickListener(view->{


        });

        society_post.setOnClickListener(view->{
            Intent intent = new Intent(this, CreateSocietyNewsfeed.class);
            intent.putExtra("societyId",sid);
            intent.putExtra("userRoll",rollNo);
            startActivity(intent);
        });

    }




}