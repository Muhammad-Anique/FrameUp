package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frameupclient.Model.SocietyOperative;
import com.example.frameupclient.Model.SocietyOperativeAPI;
import com.example.frameupclient.Model.Visitor;
import com.example.frameupclient.Model.VisitorAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.example.frameupclient.utilities.Constants;
import com.example.frameupclient.utilities.PreferenceManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  Login extends AppCompatActivity {

    public int PasswordValidity;
    public int IsVerifiedBit;
    public String RollNumber;
    private PreferenceManager preferenceManager;
    public int memberType;
    public String firebaseEmail;
    public String firebasePassword;

    public ProgressBar pl;


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
            pl.setVisibility(View.INVISIBLE);
            if(RollNumber.compareTo("admin")==0 || RollNumber.compareTo("Admin")==0 )
            {
                Intent intent = new Intent(this, AdminHome.class);
                signIn();
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(this, UserProfile.class);
                intent.putExtra("rollNo", RollNumber);
                signIn();
                startActivity(intent);
            }
        }
        else if(getPasswordValidity()==1 && getIsVerifiedBit()==0){
            pl.setVisibility(View.INVISIBLE);
            System.out.println("notverified");
            Intent intent = new Intent(this, OTP_verification.class);
            startActivity(intent);
            error.setText("Account is Not Verified");

        }
        else if(getPasswordValidity()==0 && getIsVerifiedBit()==1){
            pl.setVisibility(View.INVISIBLE);
            error.setText("Wrong Password");
            System.out.println("worng pass");
        }
        else
        {
            pl.setVisibility(View.INVISIBLE);
            error.setText("Wrong Password");
            System.out.println("wrong pass");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferenceManager = new PreferenceManager(getApplicationContext());
        //initalization
        TextInputEditText Roll = findViewById(R.id.rollno_lg_tf);
        TextInputEditText Pass = findViewById(R.id.password_lg_tf);
        pl=findViewById(R.id.progress_login);
        pl.setVisibility(View.INVISIBLE);
        Button login = findViewById(R.id.log_btn);

        //login click
        login.setOnClickListener(view -> {

            pl.setVisibility(View.VISIBLE);
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

                    firebaseEmail =response.body().getEmail();
                    firebasePassword=response.body().getPassword();

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


    private void signIn(){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .whereEqualTo(Constants.KEY_EMAIL, firebaseEmail)
                .whereEqualTo(Constants.KEY_PASSWORD, firebasePassword)
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult() != null && task.getResult().getDocuments().size()>0){
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                        preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                        preferenceManager.putString(Constants.KEY_USERID, documentSnapshot.getId());
                        preferenceManager.putString(Constants.KEY_NAME, documentSnapshot.getString(Constants.KEY_NAME));
                        preferenceManager.putString(Constants.KEY_IMAGE, documentSnapshot.getString(Constants.KEY_IMAGE));

                    } else{
                        showToast("Enable to SignIn");
                    }
                });
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}