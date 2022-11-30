package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.frameupclient.R;

public class CreateSocietyNewsfeed extends AppCompatActivity {

    Button postit,eventit,pollit;
    String rollNo;
    TextView tc;
    int sid;
    int memType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_society_newsfeed);
        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));


        postit=findViewById(R.id.create_post_news_btn);
        eventit=findViewById(R.id.create_event_news_btn3);
        pollit=findViewById(R.id.create_poll_news_btn2);
        tc=findViewById(R.id.create_n_info);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo = extras.getString("rollNo");
            sid = extras.getInt("societyId");
            memType = extras.getInt("memType");
        }

        tc.setVisibility(View.INVISIBLE);

        switch (memType){
            case 1:
                eventit.setVisibility(View.VISIBLE);
                postit.setVisibility(View.VISIBLE);
                pollit.setVisibility(View.VISIBLE);
                break;
            case 2:
                eventit.setVisibility(View.INVISIBLE);
                break;
            case 3:
                eventit.setVisibility(View.INVISIBLE);
                break;
            case 4:
                eventit.setVisibility(View.INVISIBLE);
                postit.setVisibility(View.INVISIBLE);
                pollit.setVisibility(View.INVISIBLE);
                tc.setVisibility(View.VISIBLE);
                tc.setText("Become Member of the Society to create Posts and Polls");
                break;
        }

        postit.setOnClickListener(view->{
            Intent intent = new Intent(this, CreatePost.class);
            intent.putExtra("rollNo", rollNo);
            intent.putExtra("sid", sid);
            startActivity(intent);
        });

        eventit.setOnClickListener(view->{
            Intent intent = new Intent(this,CreateEvent.class);
            intent.putExtra("rollNo", rollNo);
            intent.putExtra("sid", sid);
            startActivity(intent);
        });


        pollit.setOnClickListener(view->{
            Intent intent = new Intent(this, CreatePoll.class);
            intent.putExtra("rollNo", rollNo);
            intent.putExtra("sid", sid);
            startActivity(intent);
        });
    }
}