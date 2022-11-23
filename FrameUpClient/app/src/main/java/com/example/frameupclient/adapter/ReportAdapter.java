package com.example.frameupclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frameupclient.Model.Report;
import com.example.frameupclient.R;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportHolder> {

    private List<Report> reportList;
    public ReportAdapter(List<Report>reportList){
        this.reportList=reportList;
    }


    @NonNull
    @Override
    public ReportHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_report_list,parent,false);
        return new ReportHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportHolder holder, int position) {
        Report report=reportList.get(position);
        holder.DateReportCreated.setText(report.getDateReportCreated());
        holder.SocietyName.setText(report.getSocietyName());

    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }
}
