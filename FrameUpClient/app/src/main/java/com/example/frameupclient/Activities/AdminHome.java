package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.frameupclient.R;

public class AdminHome extends AppCompatActivity {

    Button create_society,view_notices,create_notice, messenger_btn,management_btn;
    CardView create_report,view_society;
    ImageView logout_admin;
    String rollNo ="admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        intializeComponents();
    }

    private void intializeComponents() {
        create_report=findViewById(R.id.create_report_clickable);
        view_society=findViewById(R.id.View_society_Clickable);
        view_notices=findViewById(R.id.admin_view_notice);
        create_notice=findViewById(R.id.admin_issue_notice);
        create_society=findViewById(R.id.admin_create_society_btn);
        messenger_btn=findViewById(R.id.messagener_icon_ah);
        messenger_btn.setVisibility(View.INVISIBLE);
        management_btn=findViewById(R.id.admin_management);
        logout_admin=findViewById(R.id.imageView_logo_admin_home);

        logout_admin.setOnClickListener(view->{
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });

        create_report.setOnClickListener(view->{
            Intent intent = new Intent(this, CreateReport.class);
            intent.putExtra("rollNo","admin");
            startActivity(intent);
        });

        view_notices.setOnClickListener(view->{
            Intent intent = new Intent(this, RequestList.class);
            intent.putExtra("rollNo",rollNo);
            startActivity(intent);
        });

        create_notice.setOnClickListener(view->{
            Intent intent = new Intent(this, CreateNotice.class);
            intent.putExtra("rollNo","admin");
            startActivity(intent);
        });


        management_btn.setOnClickListener(view->{
            Intent intent = new Intent(this, Deletions.class);
            intent.putExtra("rollNo","admin");
            startActivity(intent);
        });

        view_society.setOnClickListener(view->{
            Intent intent = new Intent(this, ViewSociety.class);
            intent.putExtra("rollNo","admin");
            startActivity(intent);
        });

        create_society.setOnClickListener(view->{
            Intent intent = new Intent(this, CreateSociety.class);
            intent.putExtra("rollNo","admin");
            startActivity(intent);
        });


    }




}