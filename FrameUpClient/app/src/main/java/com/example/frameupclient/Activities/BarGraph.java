package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import com.example.frameupclient.Model.ReportAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class BarGraph extends AppCompatActivity {
    BarChart barChart;
    BarDataSet barDataSet1, barDataSet2;
    ArrayList barEntries;
    String[] days = {"Sunday", "Monday", "Tuesday", "Thursday", "Friday", "Saturday"};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiity_bargraph);

        barChart = findViewById(R.id.idBarChart);

        barDataSet1 = new BarDataSet(getBarEntriesOne(), "First Set");
        barDataSet1.setColor(getApplicationContext().getResources().getColor(R.color.purple_200));
        barDataSet2 = new BarDataSet(getBarEntriesTwo(), "Second Set");
        barDataSet2.setColor(Color.BLUE);

        BarData data = new BarData(barDataSet1, barDataSet2);
        barChart.setData(data);
        barChart.getDescription().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        String[] Batch = {"19","20"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(Batch));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(3);
        float barSpace = 0.1f;
        float groupSpace = 0.5f;
        data.setBarWidth(0.15f);
        barChart.getXAxis().setAxisMinimum(0);
        barChart.animate();
        barChart.groupBars(0, groupSpace, barSpace);
        barChart.invalidate();

    }

    private List<BarEntry> getBarEntriesTwo() {
        barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1f, 4));
        barEntries.add(new BarEntry(2f, 6));
        barEntries.add(new BarEntry(3f, 8));
        barEntries.add(new BarEntry(4f, 2));
        barEntries.add(new BarEntry(5f, 4));
        barEntries.add(new BarEntry(6f, 1));

        return barEntries;
    }

    private List<BarEntry> getBarEntriesOne() {
        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1f, 8));
        barEntries.add(new BarEntry(2f, 12));
        barEntries.add(new BarEntry(3f, 4));
        barEntries.add(new BarEntry(4f, 1));
        barEntries.add(new BarEntry(5f, 7));
        barEntries.add(new BarEntry(6f, 3));
        return barEntries;
    }
}


//    BarChart barChart;
//    BarDataSet barDataSet1, barDataSet2;
//    ArrayList barEntries;
//    String[] days = {"Sunday", "Monday", "Tuesday", "Thursday", "Friday", "Saturday"};
//
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activiity_bargraph);
//
//        barChart = findViewById(R.id.idBarChart);
//
//        barDataSet1 = new BarDataSet(getBarEntriesOne(), "First Set");
//        barDataSet1.setColor(getApplicationContext().getResources().getColor(R.color.purple_200));
//        barDataSet2 = new BarDataSet(getBarEntriesTwo(), "Second Set");
//        barDataSet2.setColor(Color.BLUE);
//        int currentYear = 20;
//        BarData data = new BarData(barDataSet1, barDataSet2);
//        barChart.setData(data);
//        barChart.getDescription().setEnabled(false);
//        XAxis xAxis = barChart.getXAxis();
//       // String[] Batch = new String[0];
////        for (int i=0; i<5; i++)
////        {
////            if(currentYear!=NULL)
////            {
////                Batch[i]=currentYear.toString();
////            }
////
////        }
//        String[] Batch = {"16","17","18","19","20"};
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(Batch));
//        xAxis.setCenterAxisLabels(true);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setGranularity(1);
//        xAxis.setGranularityEnabled(true);
//        barChart.setDragEnabled(true);
//        barChart.setVisibleXRangeMaximum(3);
//        float barSpace = 0.1f;
//        float groupSpace = 0.5f;
//        data.setBarWidth(0.15f);
//        barChart.getXAxis().setAxisMinimum(0);
//        barChart.animate();
//        barChart.groupBars(0, groupSpace, barSpace);
//        barChart.invalidate();
//
//    }
//    int batchNo=20;
//    float xAxis= 1F;
//
//
////Female
//    private List<BarEntry> getBarEntriesTwo() {
//        barEntries = new ArrayList<>();
//         int femaleCount = 0;
//        RetrofitService retrofitService=new RetrofitService();
//        ReportAPI reportAPI=retrofitService.getRetrofit().create(ReportAPI.class);
////        reportAPI.getMaleByBatch(batchNo,"Music",0)
////                .enqueue(new Callback<Report>() {
////                    @Override
////                    public void onResponse(Call<Report> call, Response<Report> response) {
////                       femaleCount[0] =reportAPI.getMaleByBatch(batchNo,"Music",1);
////                    }
////
////                    @Override
////                    public void onFailure(Call<Report> call, Throwable t) {
////
////                    }
////                });
//
//        for(int i=0; i<5;i++)
//        {
//             femaleCount=reportAPI.getMaleByBatch(batchNo,"Music",0);
//            barEntries.add(new BarEntry(xAxis,femaleCount));
//            xAxis++;
//            batchNo--;
//        }
//
////        barEntries.add(new BarEntry(1f, 4));
////        barEntries.add(new BarEntry(2f, 6));
////        barEntries.add(new BarEntry(3f, 8));
////        barEntries.add(new BarEntry(4f, 2));
////        barEntries.add(new BarEntry(5f, 4));
////        barEntries.add(new BarEntry(6f, 1));
//
//        return barEntries;
//    }
////MALE
//
//    private List<BarEntry> getBarEntriesOne() {
//        barEntries = new ArrayList<>();
//        long maleCount=0;
//        batchNo=20;
//        xAxis=1F;
//        RetrofitService retrofitService=new RetrofitService();
//        ReportAPI reportAPI=retrofitService.getRetrofit().create(ReportAPI.class);
//        for (int i=0; i<5; i++)
//        {
//            maleCount=reportAPI.getMaleByBatch(batchNo,"Music",1);
//            barEntries.add(new BarEntry(xAxis,maleCount));
//            xAxis++;
//            batchNo--;
//
//        }
////        barEntries.add(new BarEntry(1f, 8));
////        barEntries.add(new BarEntry(2f, 12));
////        barEntries.add(new BarEntry(3f, 4));
////        barEntries.add(new BarEntry(4f, 1));
////        barEntries.add(new BarEntry(5f, 7));
////        barEntries.add(new BarEntry(6f, 3));
//        return barEntries;
//    }
//}