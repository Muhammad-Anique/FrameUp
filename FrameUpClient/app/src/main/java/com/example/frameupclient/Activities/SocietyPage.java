package com.example.frameupclient.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SocietyPage extends AppCompatActivity {


    //RatingSystemDialog

    Dialog d;
    AlertDialog dialog;
    //Textviews
    TextView society_id,society_heading,society_tag, society_description, society_rating_val_card, society_member_count,manage_user_card_text;
    Button  rate, joinUs, viewMember;
    ImageView society_bg;
    CardView societyAnalytics, society_post;
    String rollNo;
    int demand;
    int sid;
    int memType; //1 for head, 2 for advisor, 3 for member, 4 for visitor, 5 for request sent member, 6 request sent advisor
    boolean intiate = false;

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
        manage_user_card_text=findViewById(R.id.manage_users_tv);
        society_bg=findViewById(R.id.SocietyBackgroundImage);






        //Buttons
        societyAnalytics=findViewById(R.id.create_report_clickable);
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
            rollNo = extras.getString("rollNo");
            sid = extras.getInt("societyId");
        }





        if(rollNo.compareTo("admin")==0){
            manage_user_card_text.setText("Delete Society");
            joinUs.setText("Delete");
        }




        //getRating
        RetrofitService retrofitService1 = new RetrofitService();

        //check who is he
        SocietyAPI societyAPI = retrofitService1.getRetrofit().create(SocietyAPI.class);
        societyAPI.whoIsThis(sid, rollNo).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                memType=Integer.valueOf(response.body());
                System.out.println("response " + memType);
                initialize();


            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });


    }


    public void initialize(){
        RetrofitService retrofitService1 = new RetrofitService();



        //check who is he
        SocietyAPI societyAPI = retrofitService1.getRetrofit().create(SocietyAPI.class);
        societyAPI.getSocietyById(sid).enqueue(new Callback<Society>() {
            @Override
            public void onResponse(Call<Society> call, Response<Society> response) {
                System.out.println(response.body());
                society_heading.setText(response.body().getSocietyName());
                society_tag.setText(response.body().getSocietyTagline());
                society_description.setText(response.body().getSocietyDescription());
                society_id.setText(String.valueOf(response.body().getSocietyId()));
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                DatabaseReference getImage = databaseReference.child("image");
                getImage.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String societyImageLink = response.body().getSocietyBackground();
                                if(societyImageLink!=null){
                                    Picasso.get().load(societyImageLink).into(society_bg);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

            }

            @Override
            public void onFailure(Call<Society> call, Throwable t) {

            }
        });


        SocietyParticipationAPI societyParticipationAPI =retrofitService1.getRetrofit().create(SocietyParticipationAPI.class);
        societyParticipationAPI.getRatingBySID(sid).enqueue(new Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, Response<Float> response) {
                String rate =String.valueOf(response.body());
                String value = "0.0";
                if(rate.length()>3)
                    value=rate.substring(0,4);
                else
                    value=rate;

                society_rating_val_card.setText((String.valueOf(value)));
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
            intent.putExtra("rollNo",rollNo);
            startActivity(intent);
        });


        if(rollNo.compareTo("admin")==0){
            manage_user_card_text.setText("Delete Society");
            joinUs.setVisibility(View.VISIBLE);
            joinUs.setText("delete");
        }
        else {

            switch (memType) {
                case 1:
                    System.out.println("here");
                    manage_user_card_text.setText("Hi Head, Manage Members");
                    joinUs.setVisibility(View.VISIBLE);
                    joinUs.setText("Manage");
                    break;
                case 2:
                    manage_user_card_text.setText("Hi Advisor !! ");
                    joinUs.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    manage_user_card_text.setText("Hi Dear Member ........");
                    joinUs.setText("Become");
                    joinUs.setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    RetrofitService retrofitService3 = new RetrofitService();
                    RequestAPI requestAPI2 = retrofitService3.getRetrofit().create(RequestAPI.class);
                    requestAPI2.getRequestTypeByRoll(rollNo, "becomeMember", sid).enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            System.out.println("hey i am in reqqy");
                            if (response.body() != null) {
                                if (Integer.valueOf(response.body()) > 0) {
                                    joinUs.setVisibility(View.INVISIBLE);
                                    manage_user_card_text.setText("You Request has been Sent");

                                } else {
                                    manage_user_card_text.setText("Join our Society");
                                    joinUs.setVisibility(View.VISIBLE);
                                    joinUs.setText("Join");
                                }
                            } else {
                                manage_user_card_text.setText("Join our Society");
                                joinUs.setVisibility(View.VISIBLE);
                                joinUs.setText("Join");
                            }

                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            manage_user_card_text.setText("Join our Society");
                            joinUs.setVisibility(View.VISIBLE);
                            joinUs.setText("Join");
                        }
                    });

                    break;

            }

        }


        joinUs.setOnClickListener(view->{
            if(rollNo.compareTo("admin")!=0) {
                RetrofitService retrofitService = new RetrofitService();
                RequestAPI requestAPI = retrofitService.getRetrofit().create(RequestAPI.class);
                SocietyOperativeAPI societyOperativeAPI = retrofitService.getRetrofit().create(SocietyOperativeAPI.class);
                Request request = new Request();
                switch(memType){
                    case 1:
                        Intent intent = new Intent(this,RemoveMember.class);
                        intent.putExtra("societyId",sid);
                        intent.putExtra("rollNo",rollNo);
                        startActivity(intent);
                        break;
                    case 4:
                        request.setRequestColor("blue");
                        request.setRequestSubject("Become Member");
                        request.setRequestType("becomeMember");
                        request.setSendBy(rollNo);
                        societyOperativeAPI.getSocietyOperativeByRollAndType(sid, 1).enqueue(new Callback<List<SocietyOperative>>() {
                            @Override
                            public void onResponse(Call<List<SocietyOperative>> call, Response<List<SocietyOperative>> response) {
                                request.setSendTo(response.body().get(0).getOperativeRoll());
                                request.setRequestText("Make me Member of the Society");
                                request.setSocietyId(sid);
                                requestAPI.save(request).enqueue(new Callback<Request>() {
                                    @Override
                                    public void onResponse(Call<Request> call, Response<Request> response) {
                                        joinUs.setVisibility(View.INVISIBLE);
                                        manage_user_card_text.setText("Your request has been sent");
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
                        break;
                }

            }
            else if(rollNo.compareTo("admin")==0){

                Intent intent = new Intent(this, ViewSociety.class);
                intent.putExtra("rollNo",rollNo);

                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("AlertDialog");
                builder.setMessage("Do you really want to delete the society?");
                // add the buttons

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SocietyAPI societyAPI = retrofitService1.getRetrofit().create(SocietyAPI.class);
                        societyAPI.deleteSocietyById(sid).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                societyAPI.deleteMembersAndOperatives(sid).enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        builder.setTitle("Society is Delete");
                                        builder.setMessage("Your Move Cannot Be Undo!!");
                                        dialog.cancel();
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {

                                    }
                                });

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                // create and show the alert dialog
                dialog = builder.create();
                dialog.show();

            }

        });

        viewMember.setOnClickListener(view->{
            Intent intent = new Intent(this,UserList.class);
            demand = 1;
            intent.putExtra("demand",demand);
            intent.putExtra("societyId",sid);
            intent.putExtra("rollNo",rollNo);
            startActivity(intent);

        });

        societyAnalytics.setOnClickListener(view->{
            Intent intent = new Intent(this, ViewReport.class);
            intent.putExtra("societyId",sid);
            intent.putExtra("rollNo",rollNo);
            startActivity(intent);

        });


        society_post.setOnClickListener(view->{
            Intent intent = new Intent(this, CreateSocietyNewsfeed.class);
            intent.putExtra("societyId",sid);
            System.out.println("sid = " + sid);
            System.out.println("memtype = "+ memType);
            intent.putExtra("memType",memType);
            intent.putExtra("rollNo",rollNo);
            startActivity(intent);
        });


    }


}

