package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frameupclient.Model.Person;
import com.example.frameupclient.Model.Visitor;
import com.example.frameupclient.Model.VisitorAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public int PasswordValidity;

    public void setPasswordValidity(int passwordValidity) {
        PasswordValidity = passwordValidity;
    }

    public int getPasswordValidity() {
        return PasswordValidity;
    }

    public void Inte()
    {
        if(getPasswordValidity()==1){
            System.out.println("homr");
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);}
        else{
            System.out.println("AccountNot Not");
            Toast.makeText(this, "AccountNotExists", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText Roll = findViewById(R.id.rollno_lg_tf);
        TextInputEditText Pass = findViewById(R.id.password_lg_tf);

        Button login = findViewById(R.id.log_btn);
        login.setOnClickListener(view -> {

            String password = String.valueOf(Pass.getText());
            String roll = String.valueOf(Roll.getText());
            System.out.println("*****************************88\n");
            System.out.println(roll);
            System.out.println("*****************************88\n");

            RetrofitService retrofitService = new RetrofitService();
            VisitorAPI visitorAPI =  retrofitService.getRetrofit().create(VisitorAPI.class);

            visitorAPI.getVisitorByRollNo(roll.toString()).enqueue(new Callback<Visitor>() {
                @Override
                public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                    System.out.println(response.body());
                    if(password.equals(response.body().getPassword().toString()))
                    {
                        System.out.println(response.body());
                        setPasswordValidity(1);
                        Toast.makeText(MainActivity.this, "PasswordMatched", Toast.LENGTH_SHORT).show();
                        Inte();
                    }
                }

                @Override
                public void onFailure(Call<Visitor> call, Throwable t) {
                    setPasswordValidity(0);
                    System.out.println("~~~~Failure~~~~");
                    Toast.makeText(MainActivity.this, "AccountDoesNotExists", Toast.LENGTH_SHORT).show();
                }
            });



        });




        Button reg = findViewById(R.id.reg_btn);
        reg.setOnClickListener(view->{
            Intent intent =new Intent(this, Registeration.class);
            startActivity(intent);
        });
    }


}