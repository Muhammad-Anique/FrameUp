package com.example.frameupclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.frameupclient.Model.Gender;
import com.example.frameupclient.Model.Visitor;
import com.example.frameupclient.Model.VisitorAPI;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registeration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        intializeComponents();
    }

    private void intializeComponents() {
        TextInputEditText name_TF = findViewById(R.id.fullname_tf);
        TextInputEditText email_TF = findViewById(R.id.email_tf);
        TextInputEditText rollNo_TF = findViewById(R.id.roll_tf);
        TextInputEditText phone_TF = findViewById(R.id.phone_tf);
        TextInputEditText password_TF = findViewById(R.id.password_tf);

        Button verify = findViewById(R.id.verify_btn);

        RetrofitService retrofitService = new RetrofitService();
        VisitorAPI visitorAPI =  retrofitService.getRetrofit().create(VisitorAPI.class);

        verify.setOnClickListener(view -> {
            String name = String.valueOf(name_TF.getText());
            String email = String.valueOf(email_TF.getText());
            String rollno = String.valueOf(rollNo_TF.getText());
            String phone = String.valueOf(phone_TF.getText());
            String password = String.valueOf(password_TF.getText());

            Gender g = Gender.Male;
            int i = (int)(Math.random() * 1000000);
            String otp = Integer.toString(i);
            Visitor v = new Visitor();
            v.setAccountStatus("Active");
            v.setIsVerified(false);
            v.setOTP(otp);
            v.setPassword(password);
            v.setEmail(email);
            v.setName(name);
            v.setJoiningDate("30-Dec-2022");
            v.setGender(g);

            visitorAPI.save(v).enqueue(new Callback<Visitor>() {
                @Override
                public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                    Toast.makeText(Registeration.this, "SaveFinished", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Visitor> call, Throwable t) {
                    Toast.makeText(Registeration.this, "ErrorOccurred", Toast.LENGTH_SHORT).show();
                    Logger.getLogger(Registeration.class.getName()).log(Level.SEVERE, "Error Anique", t);
                }

            });



        });



    }
}