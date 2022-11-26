package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.frameupclient.Model.Report;
import com.example.frameupclient.Model.ReportAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateReport extends AppCompatActivity {

    TextInputEditText report_subject, report_body,report_conclusion;
    Button CreateReport;
    int sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);

        report_subject=findViewById(R.id.Report_Subject);
        report_body=findViewById(R.id.Report_Subject);
        report_conclusion=findViewById(R.id.Report_Conculsion);
        CreateReport=findViewById(R.id.create_report_btn_rr);
        CreateReport.setOnClickListener(view -> {
            Date c = Calendar.getInstance().getTime();
            System.out.println("Current time => " + c);
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            String formattedDate = df.format(c);
            String rs= String.valueOf(report_subject.getText());
            String rb=String.valueOf(report_body.getText());
            String rc=String.valueOf(report_conclusion.getText());
            RetrofitService retrofitService = new RetrofitService();
            ReportAPI reportAPI = retrofitService.getRetrofit().create(ReportAPI.class);
            Report report =new Report();
            report.setReportType("SocietyReport");
            report.setReportSubject(rs);
            report.setReportBody(rb);
            report.setSocietyId(sid);
            report.setReportConclusion(rc);
            report.setDateReportCreated(formattedDate);
            reportAPI.save(report).enqueue(new Callback<Report>() {
                @Override
                public void onResponse(Call<Report> call, Response<Report> response) {

                }

                @Override
                public void onFailure(Call<Report> call, Throwable t) {

                }
            });

        });


    }
}