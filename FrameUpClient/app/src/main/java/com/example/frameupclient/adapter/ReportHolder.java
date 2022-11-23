package com.example.frameupclient.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frameupclient.R;

public class ReportHolder extends RecyclerView.ViewHolder {

    TextView DateReportCreated,SocietyName;
    public ReportHolder(@NonNull View itemView) {
        super(itemView);
        DateReportCreated=itemView.findViewById(R.id.Date);
        SocietyName=itemView.findViewById(R.id.List_Society_Name);
    }
}
