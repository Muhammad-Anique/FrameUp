package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.frameupclient.Model.Gender;
import com.example.frameupclient.Model.Person;
import com.example.frameupclient.Model.Visitor;
import com.example.frameupclient.Model.VisitorAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
        TextInputEditText phone_TF = findViewById(R.id.phone_tf);
        TextInputEditText password_TF = findViewById(R.id.password_tf);
        RadioButton male = (RadioButton) findViewById(R.id.male);
        RadioButton female = (RadioButton) findViewById(R.id.female);
        Button verify = findViewById(R.id.verify_btn);



        boolean maleState = male.isChecked();
        boolean femaleState = female.isChecked();

        male.setOnClickListener(view -> {female.setChecked(false);});
        female.setOnClickListener(view -> {male.setChecked(false);});


        RetrofitService retrofitService = new RetrofitService();
        VisitorAPI visitorAPI =  retrofitService.getRetrofit().create(VisitorAPI.class);

        verify.setOnClickListener(view -> {
            String name = String.valueOf(name_TF.getText());
            String email = String.valueOf(email_TF.getText());
            String phone = String.valueOf(phone_TF.getText());
            String password = String.valueOf(password_TF.getText());
            char[] r = new char[8] ;
             r[0] = email.charAt(1);
             r[1] = email.charAt(2);
             r[2] = email.charAt(0);
             r[3] = '-';
             r[4] = email.charAt(3);
             r[5] = email.charAt(4);
             r[6] = email.charAt(5);
             r[7] = email.charAt(6);

            String rollno = new String(r);

            visitorAPI.getPersonByRollNo("20l-2171").enqueue(new Callback<Person>() {
                @Override
                public void onResponse(Call<Person> call, Response<Person> response) {
                    Toast.makeText(Registeration.this, "HEHEHEHEHEHEEHHE", Toast.LENGTH_SHORT).show();
                    System.out.println(response.body());
                }

                @Override
                public void onFailure(Call<Person> call, Throwable t) {
                    Toast.makeText(Registeration.this, "ERRRRROORORORORORO", Toast.LENGTH_SHORT).show();
                }
            });

            Gender g = Gender.Male;

            if(maleState)
            {
                 g = Gender.Male;
            }
            else if(femaleState){
                 g = Gender.Female;
            }

            int i = (int)(Math.random() * 1000000);
            String otp = Integer.toString(i);
            Visitor v = new Visitor();
            v.setAccountStatus("Active");
            v.setIsVerified(false);
            v.setOTP(otp);
            v.setPassword(password);
            v.setEmail(email);
            v.setName(name);
            v.setRollNo(rollno);
            v.setPhoneNumber(phone);
            Date c = Calendar.getInstance().getTime();
            System.out.println("Current time => " + c);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String formattedDate = df.format(c);
            v.setJoiningDate(formattedDate);
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