package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.frameupclient.Model.Visitor;
import com.example.frameupclient.Model.VisitorAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.example.frameupclient.utilities.Constants;
import com.example.frameupclient.utilities.PreferenceManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTP_verification extends AppCompatActivity {
    public String userEmail;
    public String rollNo;
    public String password;
    public String name;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        preferenceManager = new PreferenceManager(getApplicationContext());
        intializeComponents();
    }

    public void  start_login_activity(){
        Intent intent =new Intent(this, Login.class);
        startActivity(intent);
    }

    public void verifyUserOTP(String otpRetrievedFromDatabase, String OtpEntered){

        TextView error = findViewById(R.id.error_verify);

        RetrofitService retrofitService = new RetrofitService();
        VisitorAPI visitorAPI =  retrofitService.getRetrofit().create(VisitorAPI.class);


        if(otpRetrievedFromDatabase.compareTo(OtpEntered)==0)
        {
            error.setText("OTP Verified");
            visitorAPI.updateStatus(rollNo).enqueue(new Callback<Visitor>() {
                @Override
                public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                    error.setText("Successfully Verified Your Account is Active");
                    start_login_activity();
                }

                @Override
                public void onFailure(Call<Visitor> call, Throwable t) {
                    error.setText("Failure in OTP verification");

                }
            });

        }

    }

    private void intializeComponents() {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userEmail = extras.getString("userEmail");//The key argument here must match that used in the other activity
            rollNo =extras.getString("rollNo");
            name = extras.getString("userName");
            password =extras.getString("userPassword");
        }

        EditText n1 =  findViewById(R.id.num1);
        EditText n2 =  findViewById(R.id.num2);
        EditText n3 =  findViewById(R.id.num3);
        EditText n4 =  findViewById(R.id.num4);
        Button vb = findViewById(R.id.verify_otp_btn);


        TextView em = findViewById(R.id.userEmailid);
        TextView error = findViewById(R.id.error_verify);
        em.setText(userEmail);


        RetrofitService retrofitService = new RetrofitService();
        VisitorAPI visitorAPI =  retrofitService.getRetrofit().create(VisitorAPI.class);


        vb.setOnClickListener(view -> {

            String D1 = String.valueOf(n1.getText());
            String D2 = String.valueOf(n2.getText());
            String D3 = String.valueOf(n3.getText());
            String D4 = String.valueOf(n4.getText());

            String OTPEntered =  D1.concat(D2.concat(D3.concat(D4)));

            System.out.println(OTPEntered);

            visitorAPI.getOTPByRollNo(rollNo).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    verifyUserOTP(response.body().toString(), OTPEntered);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    error.setText("Failure Retrieving OTP From Database");
                }
            });

        });



    }





}