package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.frameupclient.Model.Report;
import com.example.frameupclient.Model.ReportAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.example.frameupclient.adapter.ReportAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportList extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);

        recyclerView=findViewById(R.id.reportList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton floatingActionButton=findViewById(R.id.reportList_fab);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent=new Intent(this,CreateReport.class);
            startActivity(intent);
        });
        loadReport();

    }

    private void loadReport() {

        RetrofitService retrofitService=new RetrofitService();
        ReportAPI reportAPI=retrofitService.getRetrofit().create(ReportAPI.class);
        reportAPI.getAllReport()
                .enqueue(new Callback<List<Report>>() {
                    @Override
                    public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Report>> call, Throwable t) {
                        Toast.makeText(ReportList.this, "Failed to Load Report", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateListView(List<Report> reportList) {
        ReportAdapter reportAdapter=new ReportAdapter(reportList);
        recyclerView.setAdapter(reportAdapter);
    }
}