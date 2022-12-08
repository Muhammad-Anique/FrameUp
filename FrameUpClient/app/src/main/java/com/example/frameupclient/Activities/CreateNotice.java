package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frameupclient.Model.Request;
import com.example.frameupclient.Model.RequestAPI;
import com.example.frameupclient.Model.SocietyParticipation;
import com.example.frameupclient.Model.SocietyParticipationAPI;
import com.example.frameupclient.Model.Visitor;
import com.example.frameupclient.Model.VisitorAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateNotice extends AppCompatActivity {

    String rollNo;
    TextInputEditText IssueType,IssueSubject,IssueComment,VictimRoll,society_ideee;
    ImageView img;
    TextView heading,userName,userEmail;;
    Button sendNotice;
    ProgressBar progressBar;
    ConstraintLayout preview;
    List<String> allRolls;
    int sid;

    String issue_type,issue_subject,issue_comment,victim_roll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notice);
        intializeComponents();
    }

    private void intializeComponents() {

        IssueComment=findViewById(R.id.Issue_Request);
        VictimRoll=findViewById(R.id.Issue_Request_Send_To);
        IssueType=findViewById(R.id.Issue_Request_Type);
        IssueSubject=findViewById(R.id.Issue_Request_Subject);
        heading=findViewById(R.id.Issue_Layout_Heading);
        sendNotice=findViewById(R.id.send_notice);
        progressBar=findViewById(R.id.progressBar_notice);
        progressBar.setVisibility(View.INVISIBLE);
        userName=findViewById(R.id.name_notice_lay_rm);
        userEmail=findViewById(R.id.email_ru_notice_rm);
        society_ideee =findViewById(R.id.issue_Request_society_id);
        preview=findViewById(R.id.user_preview_rm);
        preview.setVisibility(View.INVISIBLE);


        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo =extras.getString("rollNo");
            System.out.println(rollNo);
        }



        VictimRoll.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                RetrofitService retrofitService = new RetrofitService();
                VisitorAPI visitorAPI =retrofitService.getRetrofit().create(VisitorAPI.class);
                if(VictimRoll.getText().length()>5) {
                    visitorAPI.getVisitorByRollNo(VictimRoll.getText().toString()).enqueue(new Callback<Visitor>() {
                        @Override
                        public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                            if (response.body() != null) {
                                preview.setVisibility(View.VISIBLE);
                                userEmail.setText(response.body().getEmail());
                                userName.setText(response.body().getName());
                            }

                        }

                        @Override
                        public void onFailure(Call<Visitor> call, Throwable t) {
                            preview.setVisibility(View.INVISIBLE);
                        }
                    });

                }
            }
        });

        sendNotice.setOnClickListener(view->{
            progressBar.setVisibility(View.VISIBLE);
            issue_type = String.valueOf(IssueType.getText());
            issue_subject= String.valueOf(IssueSubject.getText());
            issue_comment= String.valueOf(IssueComment.getText());
            victim_roll= String.valueOf(VictimRoll.getText());
            RetrofitService retrofitService = new RetrofitService();


            if(victim_roll==null || issue_type==null || issue_type.isEmpty() || victim_roll.isEmpty() || issue_type.isEmpty())
                Toast.makeText(CreateNotice.this, "Invalid Entries", Toast.LENGTH_SHORT).show();
            else {
                if(society_ideee.getText().toString()==null || society_ideee.getText().toString().isEmpty()){
                    sid=0;
                   }
                else{
                    sid=Integer.valueOf(society_ideee.getText().toString());
                }
                if (victim_roll.compareTo("allMembers") == 0 && sid>0) {
                    System.out.println("gotot");
                    SocietyParticipationAPI societyParticipationAPI = retrofitService.getRetrofit().create(SocietyParticipationAPI.class);
                    societyParticipationAPI.getMemberBySId(sid).enqueue(new Callback<List<String>>() {
                        @Override
                        public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                            if (response.body() != null) {
                                for (int i = 0; i < response.body().size(); i++) {
                                    RequestAPI requestAPI = retrofitService.getRetrofit().create(RequestAPI.class);
                                    Request request = new Request();
                                    request.setRequestText(issue_comment);
                                    request.setSendTo(response.body().get(i));
                                    request.setRequestColor("pink");
                                    request.setSocietyId(sid);
                                    request.setRequestSubject(issue_subject);
                                    request.setRequestType(issue_type);
                                    request.setSendBy(rollNo);
                                    String index = String.valueOf(i);
                                    String total = String.valueOf(response.body().size());
                                    String rolly = response.body().get(i);

                                    requestAPI.save(request).enqueue(new Callback<Request>() {
                                        @Override
                                        public void onResponse(Call<Request> call, Response<Request> response) {
                                            Toast.makeText(CreateNotice.this, "Sent to " + rolly + " " + index + "/" + total, Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onFailure(Call<Request> call, Throwable t) {

                                        }
                                    });

                                }
                                Toast.makeText(CreateNotice.this, "The Request/Message Sent", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<String>> call, Throwable t) {
                            Toast.makeText(CreateNotice.this, "The Request/Message Failed", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });


                } else {


                    RequestAPI requestAPI = retrofitService.getRetrofit().create(RequestAPI.class);
                    Request request = new Request();
                    request.setRequestText(issue_comment);
                    request.setSendTo(victim_roll);
                    request.setRequestColor("Red");
                    request.setSocietyId(0);
                    request.setRequestSubject(issue_subject);
                    request.setRequestType(issue_type);
                    request.setSendBy(rollNo);

                    requestAPI.save(request).enqueue(new Callback<Request>() {
                        @Override
                        public void onResponse(Call<Request> call, Response<Request> response) {
                            Toast.makeText(CreateNotice.this, "The Request/Message Has Been Sent", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onFailure(Call<Request> call, Throwable t) {
                            Toast.makeText(CreateNotice.this, "The Request/Message  Failed", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                }

            }
        });






    }
}