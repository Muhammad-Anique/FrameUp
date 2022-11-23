package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.frameupclient.R;

public class ViewReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        TextView textView1 =findViewById(R.id.TextView1);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView reportDate=findViewById(R.id.report_Date);
       // textView1


    }
}