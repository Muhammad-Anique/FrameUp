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
import android.widget.TextView;

import com.example.frameupclient.Model.Request;
import com.example.frameupclient.Model.RequestAPI;
import com.example.frameupclient.Model.Society;
import com.example.frameupclient.Model.SocietyAPI;
import com.example.frameupclient.Model.SocietyOperative;
import com.example.frameupclient.Model.SocietyOperativeAPI;
import com.example.frameupclient.Model.SocietyParticipationAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SocietyPage extends AppCompatActivity {


    //RatingSystemDialog

    Dialog d;

    //Textviews
    TextView society_id,society_heading,society_tag, society_description, society_rating_val_card, society_member_count;
    Button  rate, joinUs, viewMember;
    CardView societyOperative, society_post;
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




        //Textview Intialiazation
        society_id =findViewById(R.id.society_id_val);
        society_heading=findViewById(R.id.society_heading_in_page);
        society_description=findViewById(R.id.Admin_Desc);
        society_tag=findViewById(R.id.society_tagline_hh);
        society_member_count=findViewById(R.id.noof_mem_sp);
        society_rating_val_card=findViewById(R.id.rating_value_society_in_page);




        //Buttons
        societyOperative=findViewById(R.id.create_report_clickable);
        society_post=findViewById(R.id.View_society_Clickable);
        viewMember=findViewById(R.id.admin_issue_notice);
        joinUs=findViewById(R.id.Manage_users);
        rate=findViewById(R.id.admin_create_society_btn);



        //Dialogs
        d=new Dialog(this);
        d.setContentView(R.layout.custom_congratulate_dialogue);
        d.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);


        //Passed Args
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo = extras.getString("userRoll");
            sid = extras.getInt("societyId");
        }



        //getRating
        RetrofitService retrofitService1 = new RetrofitService();

        SocietyAPI societyAPI=retrofitService1.getRetrofit().create(SocietyAPI.class);
        societyAPI.getSocietyById(sid).enqueue(new Callback<Society>() {
            @Override
            public void onResponse(Call<Society> call, Response<Society> response) {
                System.out.println(response.body());
                society_heading.setText(response.body().getSocietyName());
                society_tag.setText(response.body().getSocietyTagline());
                society_description.setText(response.body().getSocietyDescription());
                society_id.setText(String.valueOf(response.body().getSocietyId()));
            }

            @Override
            public void onFailure(Call<Society> call, Throwable t) {

            }
        });


        SocietyParticipationAPI societyParticipationAPI =retrofitService1.getRetrofit().create(SocietyParticipationAPI.class);
        societyParticipationAPI.getRatingBySID(sid).enqueue(new Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, Response<Float> response) {
                society_rating_val_card.setText(String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<Float> call, Throwable t) {

            }
        });

        societyParticipationAPI.getMemberCountById(sid).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                society_member_count.setText(String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });


        rate.setOnClickListener(view->{
            Intent intent = new Intent(this,RateSociety.class);
            intent.putExtra("societyId",sid);
            intent.putExtra("userRoll",rollNo);
            startActivity(intent);
        });



        joinUs.setOnClickListener(view->{
            RetrofitService retrofitService = new RetrofitService();
            RequestAPI requestAPI =retrofitService.getRetrofit().create(RequestAPI.class);
            Request request = new Request();
            request.setRequestColor("blue");
            request.setRequestSubject("Become Member");
            request.setRequestType("bm");
            request.setSendBy(rollNo);

            SocietyOperativeAPI societyOperativeAPI = retrofitService.getRetrofit().create(SocietyOperativeAPI.class);
            societyOperativeAPI.getSocietyOperativeByRollAndType(sid,1).enqueue(new Callback<List<SocietyOperative>>() {
                @Override
                public void onResponse(Call<List<SocietyOperative>> call, Response<List<SocietyOperative>> response) {
                    request.setSendTo(response.body().get(0).getOperativeRoll());
                    request.setRequestText("Make me Member of the Society");
                    request.setSocietyId(sid);
                    requestAPI.save(request).enqueue(new Callback<Request>() {
                        @Override
                        public void onResponse(Call<Request> call, Response<Request> response) {

                        }

                        @Override
                        public void onFailure(Call<Request> call, Throwable t) {

                        }
                    });

                }

                @Override
                public void onFailure(Call<List<SocietyOperative>> call, Throwable t) {

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
            intent.putExtra("societyId",sid);
            intent.putExtra("userRoll",rollNo);
            startActivity(intent);

        });


        society_post.setOnClickListener(view->{
            Intent intent = new Intent(this, CreateSocietyNewsfeed.class);
            intent.putExtra("societyId",sid);
            intent.putExtra("userRoll",rollNo);
            startActivity(intent);
        });

    }


}

