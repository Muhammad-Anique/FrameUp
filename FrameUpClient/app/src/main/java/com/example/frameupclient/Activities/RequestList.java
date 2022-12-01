package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.frameupclient.Model.Request;
import com.example.frameupclient.Model.RequestAPI;
import com.example.frameupclient.Model.RequestAdapter;
import com.example.frameupclient.Model.Society;
import com.example.frameupclient.Model.SocietyOperative;
import com.example.frameupclient.Model.SocietyOperativeAPI;
import com.example.frameupclient.Model.SocietyParticipation;
import com.example.frameupclient.Model.SocietyParticipationAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestList extends AppCompatActivity implements RequestRvInterface {

    Button society_btn, home_btn, notification_btn, profile_btn;
    RecyclerView Rv;
    ConstraintLayout nvb;
    String rollNo;
    Dialog d;
    List<Request> Req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);
        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));

        Rv=findViewById(R.id.Recyc_req_list);
        Rv.setLayoutManager(new LinearLayoutManager(this));


        society_btn=findViewById(R.id.req_page_society_button);
        home_btn=findViewById(R.id.req_page_home_btn);
        nvb=findViewById(R.id.nvrl);
        notification_btn=findViewById(R.id.req_page_noti_button);
        profile_btn=findViewById(R.id.req_profile_button);





        notification_btn.setBackgroundTintList(this.getColorStateList((R.color.Primary_Color_2)));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo = extras.getString("rollNo");
            System.out.println("Roll passes by home" + rollNo);
        }

        if(rollNo.compareTo("admin")==0){
         nvb.setVisibility(View.INVISIBLE);
        }

        loadRequests();




        home_btn.setOnClickListener(view->{
            Intent intent = new Intent(this, VisitorHome.class);
            intent.putExtra("rollNo",rollNo);
            startActivity(intent);
            finish();

        });

        society_btn.setOnClickListener(view->{
            Intent intent = new Intent(this, ViewSociety.class);
            intent.putExtra("rollNo",rollNo);
            startActivity(intent);
            finish();
        });

        profile_btn.setOnClickListener(view->{
            Intent intent = new Intent(this, UserProfile.class);
            intent.putExtra("rollNo",rollNo);
            startActivity(intent);
            finish();
        });

    }

    private void loadRequests() {
        RetrofitService retrofitService = new RetrofitService();
        RequestAPI requestAPI = retrofitService.getRetrofit().create(RequestAPI.class);

        requestAPI.getAllRequestsByRoll(rollNo).enqueue(new Callback<List<Request>>() {
            @Override
            public void onResponse(Call<List<Request>> call, Response<List<Request>> response) {
                System.out.println("Request list => "+response.body());
                populateRequestList(response.body());
                Req=response.body();
            }

            @Override
            public void onFailure(Call<List<Request>> call, Throwable t) {

            }
        });

    }

    private void populateRequestList(List<Request> body) {
        RequestAdapter requestAdapter =new RequestAdapter(body, this);
        Rv.setAdapter(requestAdapter);
    }

    @Override
    public void onButtonClick(int position) {

        RetrofitService retrofitService = new RetrofitService();
        SocietyParticipationAPI societyParticipationAPI =retrofitService.getRetrofit().create(SocietyParticipationAPI.class);
        SocietyOperativeAPI societyOperativeAPI =retrofitService.getRetrofit().create(SocietyOperativeAPI.class);

        SocietyParticipation societyParticipation = new SocietyParticipation();
        SocietyOperative societyOperative= new SocietyOperative();

       if(Req.get(position).getRequestType().compareTo("becomeMember")==0){
           societyParticipation.setSocietyId(Req.get(position).getSocietyId());
           societyParticipation.setRating(0);
           societyParticipation.setRollNo(Req.get(position).getSendBy());
           societyParticipation.setParticipatedAs(1);
           societyParticipationAPI.save(societyParticipation).enqueue(new Callback<SocietyParticipation>() {
               @Override
               public void onResponse(Call<SocietyParticipation> call, Response<SocietyParticipation> response) {
                   System.out.println("Participation Created");
                   RequestAPI requestAPI = retrofitService.getRetrofit().create(RequestAPI.class);
                   requestAPI.deleteReq(Req.get(position).getRequestId()).enqueue(new Callback<Void>() {
                       @Override
                       public void onResponse(Call<Void> call, Response<Void> response) {
                           Request congratulate = new Request();
                           congratulate.setRequestType("becameMember");
                           congratulate.setRequestSubject("Congratulations");
                           congratulate.setRequestText("Your membership request has been accepted by the head of the society. Now you have became the official member of the Society");
                           congratulate.setSendTo(Req.get(position).getSendBy());
                           requestAPI.save(congratulate).enqueue(new Callback<Request>() {
                               @Override
                               public void onResponse(Call<Request> call, Response<Request> response) {

                               }

                               @Override
                               public void onFailure(Call<Request> call, Throwable t) {

                               }
                           });
                           loadRequests();


                       }

                       @Override
                       public void onFailure(Call<Void> call, Throwable t) {

                       }
                   });
               }

               @Override
               public void onFailure(Call<SocietyParticipation> call, Throwable t) {

               }
           });
       }
       else if(Req.get(position).getRequestType().compareTo("becomeAdvisor")==0)
       {
           societyOperative.setSocietyId(Req.get(position).getSocietyId());
           societyOperative.setOperativeRoll(Req.get(position).getSendBy());
           societyOperative.setOperativeType(2);

           societyOperativeAPI.save(societyOperative).enqueue(new Callback<SocietyOperative>() {
               @Override
               public void onResponse(Call<SocietyOperative> call, Response<SocietyOperative> response) {
                   System.out.println("Operative Created");

                   RequestAPI requestAPI = retrofitService.getRetrofit().create(RequestAPI.class);
                   requestAPI.deleteReq(Req.get(position).getRequestId()).enqueue(new Callback<Void>() {
                       @Override
                       public void onResponse(Call<Void> call, Response<Void> response) {
                           loadRequests();
                       }

                       @Override
                       public void onFailure(Call<Void> call, Throwable t) {

                       }
                   });

               }

               @Override
               public void onFailure(Call<SocietyOperative> call, Throwable t) {

               }
           });


       }






    }
}