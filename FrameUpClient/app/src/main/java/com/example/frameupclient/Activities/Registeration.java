package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
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
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registeration extends AppCompatActivity {

    private boolean password_valid=false;
    private boolean email_valid=false;
    private boolean phone_valid=false;
    public boolean accountExist = false;
    boolean maleState=false;
    boolean femaleState=false;
     public boolean verifyOTP =false;


    public boolean isPassword_valid() {
        return password_valid;
    }

    public void setPassword_valid(boolean password_valid) {
        this.password_valid = password_valid;
    }

    public boolean isEmail_valid() {
        return email_valid;
    }

    public void setEmail_valid(boolean email_valid) {
        this.email_valid = email_valid;
    }

    public boolean isPhone_valid() {
        return phone_valid;
    }

    public void setPhone_valid(boolean phone_valid) {
        this.phone_valid = phone_valid;
    }

    public void start_verify_activity(String email, String Roll, String password, String name)
    {
        Intent intent =new Intent(this, OTP_verification.class);
        intent.putExtra("userEmail",email);
        intent.putExtra("rollNo", Roll);
        intent.putExtra("userName",name);
        intent.putExtra("userPassword",password);
        startActivity(intent);
    }

    protected void displayErrors()
    {
        TextView reg_error = findViewById(R.id.Reg_Error);
        if(!isPassword_valid() && isEmail_valid()) {
            reg_error.setText("Password Does Not Match");
        }
        else if(!isEmail_valid() && isPassword_valid())
        {
            reg_error.setText("Email Invalid");
        }
        else if(!isEmail_valid() && !isPassword_valid())
        {
            reg_error.setText("Email and Password Invalid");
        }
        else if(!isPhone_valid())
        {
            reg_error.setText("Phone Number Invalid");
        }
        else if(!isPassword_valid() || !isEmail_valid() || !isPhone_valid()){
           reg_error.setText("Invalid Entry in One of the Field");
        }

    }

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
        TextInputEditText confirm_password_TF = findViewById(R.id.confirm_password_tf);
        RadioButton male = (RadioButton) findViewById(R.id.male);
        RadioButton female = (RadioButton) findViewById(R.id.female);
        Button verify = findViewById(R.id.verify_btn);
        ProgressBar progressBar_reg = findViewById(R.id.reg_progress);
        progressBar_reg.setVisibility(TextView.INVISIBLE);



        TextView reg_error = findViewById(R.id.Reg_Error);

        male.setOnClickListener(view -> {female.setChecked(false); maleState=true; femaleState=false; });
        female.setOnClickListener(view -> {male.setChecked(false); maleState=false; femaleState=true; });


        RetrofitService retrofitService = new RetrofitService();
        VisitorAPI visitorAPI =  retrofitService.getRetrofit().create(VisitorAPI.class);

        verify.setOnClickListener(view -> {

            String name = String.valueOf(name_TF.getText());
            String email = String.valueOf(email_TF.getText());
            String phone = String.valueOf(phone_TF.getText());
            String password = String.valueOf(password_TF.getText());
            String confirm_password = String.valueOf(confirm_password_TF.getText());
            progressBar_reg.setVisibility(TextView.VISIBLE);
            if(name_TF.getText().toString()==null || name_TF.getText().toString().isEmpty() || email_TF.getText().toString()==null || email_TF.getText().toString().isEmpty() ||
            password_TF.getText().toString().isEmpty() || password_TF.getText().toString()==null || (!maleState  && !femaleState)){

                reg_error.setText("Invalid Entries");
            }
            else{


                System.out.println("comparing password");

                if (password.compareTo(confirm_password) != 0) {
                    setPassword_valid(false);
                } else {
                    setPassword_valid(true);
                }

                System.out.println("Comparing password");

                if (phone.length() == 11) {
                    setPhone_valid(true);
                } else {
                    setPhone_valid(false);
                }

                System.out.println("Comparing Email");


                if (email.length() == 21) {
                    String pattern = email.substring(7);
                    if (pattern.compareTo("@lhr.nu.edu.pk") == 0 && email.charAt(0) == 'l') {
                        System.out.println("Email Compared");
                        setEmail_valid(true);
                    } else {
                        setEmail_valid(false);
                    }
                } else {
                    setEmail_valid(false);
                }


                char[] r = new char[8];
                r[0] = email.charAt(1);
                r[1] = email.charAt(2);
                r[2] = email.charAt(0);
                r[3] = '-';
                r[4] = email.charAt(3);
                r[5] = email.charAt(4);
                r[6] = email.charAt(5);
                r[7] = email.charAt(6);

                String rollno = new String(r);

                Gender g = Gender.Male;

                if (maleState) {
                    g = Gender.Male;
                } else if (femaleState) {
                    g = Gender.Female;
                }


                Visitor v = new Visitor();
                v.setAccountStatus("uv");
                v.setIsVerified(false);
                v.setPassword(password);
                v.setEmail(email);
                v.setName(name);
                v.setRollNo(rollno);
                v.setPhoneNumber(phone);
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                v.setJoiningDate(formattedDate);
                v.setGender(g);

                System.out.println("*************************************************");
                System.out.println(v);

                displayErrors();
                visitorAPI.getVisitorByRollNo(rollno).enqueue(new Callback<Visitor>() {
                    @Override
                    public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                        if(response.body()!=null) {
                            Toast.makeText(Registeration.this, "Account Already Exist", Toast.LENGTH_SHORT).show();
                            progressBar_reg.setVisibility(TextView.INVISIBLE);
                        }else{
                            if (isPassword_valid() && isPhone_valid() && isEmail_valid()) {
                                reg_error.setText("All Entries are Acceptable");

                                System.out.println("sending Api");

                                visitorAPI.save(v).enqueue(new Callback<Visitor>() {
                                    @Override
                                    public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                                        Toast.makeText(Registeration.this, "Registeration Successful", Toast.LENGTH_SHORT).show();
                                        System.out.println("hi");
                                        start_verify_activity(email, rollno, name, password);
                                        progressBar_reg.setVisibility(TextView.INVISIBLE);
                                    }

                                    @Override
                                    public void onFailure(Call<Visitor> call, Throwable t) {
                                        Toast.makeText(Registeration.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                                        reg_error.setText("Account Already Exist or Server is Down");
                                        Logger.getLogger(Registeration.class.getName()).log(Level.SEVERE, "Error Anique", t);
                                    }

                                });

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Visitor> call, Throwable t) {
                        if (isPassword_valid() && isPhone_valid() && isEmail_valid()) {

                            reg_error.setText("All Entries are Acceptable");

                            System.out.println("sending Api");

                            visitorAPI.save(v).enqueue(new Callback<Visitor>() {
                                @Override
                                public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                                    Toast.makeText(Registeration.this, "Registeration Successful", Toast.LENGTH_SHORT).show();
                                    System.out.println("hi");
                                    start_verify_activity(email, rollno, name, password);
                                    progressBar_reg.setVisibility(TextView.INVISIBLE);
                                }

                                @Override
                                public void onFailure(Call<Visitor> call, Throwable t) {
                                    Toast.makeText(Registeration.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                                    reg_error.setText("Account Already Exist or Server is Down");
                                    Logger.getLogger(Registeration.class.getName()).log(Level.SEVERE, "Error Anique", t);
                                }

                            });

                        }
                    }
                });





            }
        });



    }
}