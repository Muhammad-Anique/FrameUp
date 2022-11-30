package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frameupclient.Model.RequestAPI;
import com.example.frameupclient.Model.Society;
import com.example.frameupclient.Model.SocietyParticipation;
import com.example.frameupclient.Model.SocietyParticipationAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RateSociety extends AppCompatActivity {

    RatingBar ratingBar;
    TextView ratingValue;
    Button Rate_Submit;
    TextView comment;
    float rating_val_to_submit;


    String rollNo;
    int sid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_society);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo = extras.getString("rollNo");
            sid = extras.getInt("societyId");
        }
        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        //Rating
        ratingBar=findViewById(R.id.ratingBar_page);
        ratingValue=findViewById(R.id.Rating_Value);
        Rate_Submit=findViewById(R.id.rate_it_submit);
        comment=findViewById(R.id.Rating_Selected);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int rateCase = (int) v;
                rating_val_to_submit=v;
                ratingValue.setText(String.valueOf(v));
                switch(rateCase){
                    case 1:
                        comment.setText("Sorry");
                        break;
                    case 2:
                        comment.setText("Fair");
                        break;
                    case 3:
                        comment.setText("Good");
                        break;
                    case 4:
                        comment.setText("Amazing");
                        break;
                    case 5:
                        comment.setText("Stunning");
                        break;
                }
            }
        });

        Rate_Submit.setOnClickListener(view->{
            RetrofitService retrofitService = new RetrofitService();
            SocietyParticipationAPI societyParticipationAPI =retrofitService.getRetrofit().create(SocietyParticipationAPI.class);
            SocietyParticipation societyParticipation = new SocietyParticipation();
            societyParticipation.setRating(rating_val_to_submit);
            societyParticipationAPI.updateRating(sid, rollNo, societyParticipation).enqueue(new Callback<Society>() {
                @Override
                public void onResponse(Call<Society> call, Response<Society> response) {
                   comment.setText("Successfully Submitted");
                }

                @Override
                public void onFailure(Call<Society> call, Throwable t) {
                    comment.setText("Failure");
                }
            });
        });


    }
}