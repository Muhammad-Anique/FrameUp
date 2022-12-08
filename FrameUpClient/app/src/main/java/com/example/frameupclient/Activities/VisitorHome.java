package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.frameupclient.Model.Post;
import com.example.frameupclient.Model.PostAPI;
import com.example.frameupclient.Model.RvAdapter;
import com.example.frameupclient.Model.SocietyAPI;
import com.example.frameupclient.Model.SocietyOperative;
import com.example.frameupclient.Model.SocietyOperativeAPI;
import com.example.frameupclient.Model.SocietyParticipation;
import com.example.frameupclient.Model.SocietyParticipationAPI;
import com.example.frameupclient.Model.Visitor;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitorHome extends AppCompatActivity {

    public String rollNo;
    RecyclerView rvMain;
    int MemberType;
    Button homeBTN, society_BTN, notification, poll_btn, profile_btn,messenger_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_home);


        //intializations
       homeBTN=findViewById(R.id.visitor_home_page_home_btn);
       homeBTN.setBackgroundTintList(this.getColorStateList((R.color.Primary_Color_2)));
       messenger_btn=findViewById(R.id.messagener_icon_vh);
       notification=findViewById(R.id.visitor_home_page_req_button);
       society_BTN=findViewById(R.id.visitor_home_page_society_button);
       profile_btn= findViewById(R.id.visitor_home_profile_button);
       poll_btn=findViewById(R.id.poll_btn_home);
        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        rvMain = findViewById(R.id.visitor_home_page_recycler_view);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        loadPosts();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo = extras.getString("rollNo");
            MemberType=extras.getInt("memberType");
        }


       messenger_btn.setOnClickListener(view->{
           Intent intent = new Intent(getApplicationContext(), com.example.frameupclient.Activities.StartActivity.class);
           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
           intent.putExtra("rollNo",rollNo);
           startActivity(intent);
       });



        poll_btn.setOnClickListener(view->{
            Intent intent = new Intent(this,ViewPoll.class);
            intent.putExtra("rollNo",rollNo);
            startActivity(intent);

        });

       notification.setOnClickListener(view->{
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



       society_BTN.setOnClickListener(view->{
           Intent intent = new Intent(this, ViewSociety.class);
           intent.putExtra("rollNo", rollNo);
           startActivity(intent);

       });




      profile_btn.setOnClickListener(view->{
          Intent intent = new Intent(this, UserProfile.class);
          intent.putExtra("rollNo", rollNo);
          startActivity(intent);

      });




    }





    private void loadPosts() {
        RetrofitService retrofitService = new RetrofitService();
        PostAPI postApi =  retrofitService.getRetrofit().create(PostAPI.class);
        postApi.getAllPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                populate(response.body());
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    public void populate(List<Post> p){

        RvAdapter rvAdapter = new RvAdapter(p);
        rvMain.setAdapter(rvAdapter);
    }
}