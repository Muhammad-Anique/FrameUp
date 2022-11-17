package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frameupclient.Model.Society;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.example.frameupclient.Retrofit.SocietyAPI;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateSociety extends AppCompatActivity {

    public EditText inputEditDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_society);
        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText inputEditName = findViewById(R.id.societyName_tf);
        TextInputEditText inputEditCategory = findViewById(R.id.societyCategory_tf);
        TextInputEditText inputEditDate = findViewById(R.id.Date_tf);
        inputEditDetail = findViewById(R.id.Details_tf);
        Button createButton = findViewById(R.id.create_btn);

        TextView error = findViewById(R.id.create_Error);
        RetrofitService retrofitService = new RetrofitService();
        SocietyAPI societyAPI = retrofitService.getRetrofit().create(SocietyAPI.class);

        createButton.setOnClickListener(view -> {
            String name = String.valueOf(inputEditName.getText());
            String Category = String.valueOf(inputEditCategory.getText());
            String Date = String.valueOf(inputEditDate.getText());
            String Details = String.valueOf(inputEditDetail.getText());

            Society society = new Society();
            society.setSocietyName(name);
            society.setSocietyCategory(Category);
            society.setDateCreated(Date);
            society.setSocietyMotive(Details);

            System.out.println(society);

            societyAPI.save(society).enqueue(new Callback<Society>() {
                @Override
                public void onResponse(Call<Society> call, Response<Society> response) {
                    Toast.makeText(CreateSociety.this, "Successful", Toast.LENGTH_SHORT).show();
                    System.out.println("Successful");
                }

                @Override
                public void onFailure(Call<Society> call, Throwable t) {
                    Toast.makeText(CreateSociety.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                    error.setText("Society Already Exist");
                    Logger.getLogger(Registeration.class.getName()).log(Level.SEVERE, "Error", t);
                }
            });
        });
    }

}

