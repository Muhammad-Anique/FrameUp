package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.frameupclient.R;
import com.google.android.material.textfield.TextInputEditText;

public class CreateReport extends AppCompatActivity {

    TextInputEditText report_subject, report_body,report_conclusion;
    Button CreateReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);

        report_subject=findViewById(R.id.Report_Subject);
        report_body=findViewById(R.id.Report_Subject);
        report_conclusion=findViewById(R.id.Report_Conculsion);
        CreateReport=findViewById(R.id.create_report_btn_rr);
        CreateReport.setOnClickListener(view -> {

        });


    }
}