package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frameupclient.Model.SocietyOperative;
import com.example.frameupclient.Model.SocietyOperativeAPI;
import com.example.frameupclient.Model.Visitor;
import com.example.frameupclient.Model.VisitorAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  Login extends AppCompatActivity {

    public int PasswordValidity;
    public int IsVerifiedBit;
    public String RollNumber;
    public int memberType;

    public int getIsVerifiedBit() { return IsVerifiedBit;}

    public void setIsVerifiedBit(int isVerifiedBit) {IsVerifiedBit = isVerifiedBit;}

    //setter
    public void setPasswordValidity(int passwordValidity) {
        PasswordValidity = passwordValidity;
    }
    //getter
    public int getPasswordValidity() {
        return PasswordValidity;
    }


    public void UserVerfication()
    {
        TextView error = findViewById(R.id.Error);

        if(getPasswordValidity()==1 && getIsVerifiedBit()==1){
            if(RollNumber.compareTo("admin")==0)
            {
                Intent intent = new Intent(this, AdminHome.class);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(this, VisitorHome.class);
                intent.putExtra("userRoll", RollNumber);
                startActivity(intent);
            }
        }
        else if(getPasswordValidity()==1 && getIsVerifiedBit()==0){
            System.out.println("notverified");
            Intent intent = new Intent(this, OTP_verification.class);
            startActivity(intent);
            error.setText("Account is Not Verified");

        }
        else if(getPasswordValidity()==0 && getIsVerifiedBit()==1){
            error.setText("Wrong Password");
            System.out.println("worng pass");
        }
        else
        {
            error.setText("Wrong Password");
            System.out.println("wrong pass");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initalization
        TextInputEditText Roll = findViewById(R.id.rollno_lg_tf);
        TextInputEditText Pass = findViewById(R.id.password_lg_tf);
        Button login = findViewById(R.id.log_btn);

        //login click
        login.setOnClickListener(view -> {

            //retrofitService
            RetrofitService retrofitService = new RetrofitService();
            VisitorAPI visitorAPI =  retrofitService.getRetrofit().create(VisitorAPI.class);


            //stringing
            String password = String.valueOf(Pass.getText());
            String roll = String.valueOf(Roll.getText());
            RollNumber =String.valueOf(Roll.getText());

            //consoling
            System.out.println("*****************************88\n");
            System.out.println(roll);
            System.out.println("*****************************88\n");


            //API CALLING ->> Return Visitor --> response body
            visitorAPI.getVisitorByRollNo(roll.toString()).enqueue(new Callback<Visitor>() {
                @Override
                public void onResponse(Call<Visitor> call, Response<Visitor> response) {

                    //Printing
                    System.out.println(response.body());


                    if(password.equals(response.body().getPassword().toString()))
                    {
                        System.out.println(response.body());
                        setPasswordValidity(1);
                        System.out.println("setting 1 pass bit");
                    }
                    else
                    {
                        System.out.println("setting 0 pass bit");
                        setPasswordValidity(0);
                    }


                    if(response.body().getIsVerified())
                    {
                        System.out.println("setting 1 verifiy bit");
                        setIsVerifiedBit(1);
                    }
                    else
                    {
                        System.out.println("setting  0 verifiy bit");
                        setIsVerifiedBit(0);
                    }

                    UserVerfication();


                }

                @Override
                public void onFailure(Call<Visitor> call, Throwable t) {
                    setPasswordValidity(0);
                    Toast.makeText(Login.this, "Access Failure or Account Not Exist", Toast.LENGTH_SHORT).show();
                }
            });



        });


        TextView reg = findViewById(R.id.reg_text);
        reg.setOnClickListener(view->{
            Intent intent =new Intent(this, Registeration.class);
            startActivity(intent);
        });
    }


}