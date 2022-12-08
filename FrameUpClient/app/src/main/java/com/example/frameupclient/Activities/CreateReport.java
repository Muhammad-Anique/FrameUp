package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

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

    TextInputEditText report_subject, report_body,report_conclusion,society_id;
    Button CreateReport;
    int sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);
        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        report_subject=findViewById(R.id.Report_Subject);
        report_body=findViewById(R.id.Report_Body);
        society_id=findViewById(R.id.society_id_tv);
        report_conclusion=findViewById(R.id.Report_Conculsion);
        CreateReport=findViewById(R.id.create_report_btn_rr);
        CreateReport.setOnClickListener(view -> {
            if(report_subject.getText().toString().isEmpty() || society_id.getText().toString().isEmpty() || report_conclusion.getText().toString().isEmpty() )
            {
                Toast.makeText(CreateReport.this, "Invalid Entries", Toast.LENGTH_SHORT).show();
            }
            else {
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                String rs = String.valueOf(report_subject.getText());
                String rb = String.valueOf(report_body.getText());
                String rc = String.valueOf(report_conclusion.getText());
                RetrofitService retrofitService = new RetrofitService();
                ReportAPI reportAPI = retrofitService.getRetrofit().create(ReportAPI.class);
                Report report = new Report();
                report.setReportType("SocietyReport");
                report.setReportSubject(rs);
                report.setReportBody(rb);
                report.setSocietyId(Integer.valueOf(society_id.getText().toString()));
                report.setReportConclusion(rc);
                report.setDateReportCreated(formattedDate);

                reportAPI.save(report).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(CreateReport.this, "Report Created", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(CreateReport.this, "Report Not Created", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}