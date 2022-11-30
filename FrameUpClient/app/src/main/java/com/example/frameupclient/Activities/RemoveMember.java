package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class RemoveMember extends AppCompatActivity {

    TextInputEditText removeRoll;
    Button Remove_Member;
    TextView name, email;
    int sid;
    String rollNo;
    ProgressBar p;
    boolean enrollment =false;
    String victimRollno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_member);
        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));


        removeRoll=findViewById(R.id.rm_roll);
        Remove_Member=findViewById(R.id.rm_member);
        name= findViewById(R.id.name_notice_lay_rm);
        email=findViewById(R.id.email_ru_notice_rm);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo = extras.getString("rollNo");
            sid = extras.getInt("societyId");
        }


        removeRoll.addTextChangedListener(new TextWatcher() {
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
                SocietyParticipationAPI societyParticipationAPI = retrofitService.getRetrofit().create(SocietyParticipationAPI.class);

                societyParticipationAPI.getSocietyPByRollAndSid(sid,removeRoll.getText().toString() ).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(Integer.valueOf(response.body())>0){
                        visitorAPI.getVisitorByRollNo(removeRoll.getText().toString()).enqueue(new Callback<Visitor>() {
                            @Override
                            public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                                name.setText(response.body().getEmail());
                                email.setText(response.body().getName());
                            }
                            @Override
                            public void onFailure(Call<Visitor> call, Throwable t) {
                                name.setText("Member Does not Exists");
                            }
                        });
                        }else{
                            name.setText("Member Does not Exists");
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        name.setText("Member Does not Exists");

                    }
                });


            }
        });

        Remove_Member.setOnClickListener(view->{
            RetrofitService retrofitService = new RetrofitService();
            SocietyParticipationAPI societyParticipationAPI = retrofitService.getRetrofit().create(SocietyParticipationAPI.class);
            societyParticipationAPI.deleteParticipation(sid, removeRoll.getText().toString()).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(getApplicationContext(),"Member Deleted",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });

        });



    }
}