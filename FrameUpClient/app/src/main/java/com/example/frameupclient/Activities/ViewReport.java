package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import com.example.frameupclient.Model.ReportAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class ViewReport extends AppCompatActivity {

    BarChart barChart;
    PieChart pieChart;
    BarDataSet barDataSet1, barDataSet2;
    ArrayList barEntries;
    String[] days = {"Sunday", "Monday", "Tuesday", "Thursday", "Friday", "Saturday"};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);

        barChart = findViewById(R.id.bb);

        barDataSet1 = new BarDataSet(getBarEntriesOne(), "First Set");
        barDataSet1.setColor(getApplicationContext().getResources().getColor(R.color.purple_200));
        barDataSet2 = new BarDataSet(getBarEntriesTwo(), "Second Set");
        barDataSet2.setColor(Color.BLUE);

        BarData data = new BarData(barDataSet1, barDataSet2);
        barChart.setData(data);
        barChart.getDescription().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        String[] Batch = {"18","19","20","21","22"};
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

//        barChart=findViewById(R.id.bb);
//        pieChart=findViewById(R.id.pp);
//
//        ArrayList<BarEntry> barEntryArrayList = new ArrayList<>();
//        ArrayList<PieEntry> pieEntries= new ArrayList<>();
//
//        for(int i=1;i<10;i++){
//            float value = (float) (i*10.0);
//            BarEntry barEntry = new BarEntry(i,value);
//            barEntryArrayList.add(barEntry);
//        }
//
//        BarDataSet barDataSet= new BarDataSet(barEntryArrayList,"Society Popularity");
//        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        barDataSet.setDrawValues(false);
//        barDataSet.setColor(getApplicationContext().getResources().getColor(R.color.purple_200));
//        barChart.setData(new BarData(barDataSet));
//        barChart.animateY(5000);
//        barChart.getDescription().setText("Society Poularity");
//        barChart.getDescription().setTextColor(R.color.Primary_Color_1);


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