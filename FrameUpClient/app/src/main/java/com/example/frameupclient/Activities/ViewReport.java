package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.frameupclient.Model.Report;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewReport extends AppCompatActivity {

    BarChart barChart;
    BarDataSet barDataSet1, barDataSet2;
    ArrayList barEntries;

    int sid;
    TextView ts,tb,tc;

    int mCount18,mCount19,mCount20,mCount21,mCount22;
    int fCount18,fCount19,fCount20,fCount21,fCount22;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Report report =new Report();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);


        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sid = extras.getInt("societyId");
        }


        barChart = findViewById(R.id.bb);
        ts=findViewById(R.id.report_subject_field);
        tb=findViewById(R.id.report_body_field);
        tc=findViewById(R.id.Report_Conculsion_field);

        RetrofitService retrofitService =new RetrofitService();
        ReportAPI reportAPI = retrofitService.getRetrofit().create(ReportAPI.class);
        reportAPI.getReportBySid(sid).enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {

                System.out.println(response.body());
                ts.setText(response.body().getReportSubject());
                tb.setText(response.body().getReportBody());
                tc.setText(response.body().getReportConclusion());

                fCount18= response.body().getNoOfFemalesBatch18();
                fCount19= response.body().getNoOfFemalesBatch19();
                fCount20= response.body().getNoOfFemalesBatch20();
                fCount21= response.body().getNoOfFemalesBatch21();
                fCount22= response.body().getNoOfFemalesBatch22();

                mCount18= response.body().getNoOfMalesBatch18();
                mCount19= response.body().getNoOfMalesBatch19();
                mCount20= response.body().getNoOfMalesBatch20();
                mCount21= response.body().getNoOfMalesBatch21();
                mCount22= response.body().getNoOfMalesBatch22();

                barDataSet1 = new BarDataSet(getBarEntriesOne(), "Males");
                barDataSet1.setColor(getApplicationContext().getResources().getColor(R.color.Primary_Color_1));
                barDataSet2 = new BarDataSet(getBarEntriesTwo(), "Females");
                barDataSet2.setColor(R.color.purple_200);

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

            }

            @Override
            public void onFailure(Call<Report> call, Throwable t) {

                ts.setText("Report");
                tb.setText("Request Admin to Create Report");
                tc.setText("No Report Found");

                barChart.setVisibility(View.INVISIBLE);

                fCount18= 19;
                fCount19= 8;
                fCount20=19;
                fCount21= 5;
                fCount22= 21;

                mCount18= 12;
                mCount19= 10;
                mCount20= 14;
                mCount21= 7;
                mCount22= 9;
                barDataSet1 = new BarDataSet(getBarEntriesOne(), "Males");
                barDataSet1.setColor(getApplicationContext().getResources().getColor(R.color.purple_200));
                barDataSet2 = new BarDataSet(getBarEntriesTwo(), "Females");
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

            }
        });







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

        barEntries.add(new BarEntry(1f, fCount18));
        barEntries.add(new BarEntry(2f, fCount19));
        barEntries.add(new BarEntry(3f, fCount20));
        barEntries.add(new BarEntry(4f, fCount21));
        barEntries.add(new BarEntry(5f, fCount22));

        return barEntries;
    }

    private List<BarEntry> getBarEntriesOne() {
        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1f, mCount18));
        barEntries.add(new BarEntry(2f, mCount19));
        barEntries.add(new BarEntry(3f, mCount20));
        barEntries.add(new BarEntry(4f, mCount21));
        barEntries.add(new BarEntry(5f, mCount22));

        return barEntries;
    }
}