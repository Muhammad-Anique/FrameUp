package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.frameupclient.R;

public class CreatePoll extends AppCompatActivity {

    // Initialize variable
    SeekBar seekBar1,seekBar2,seekBar3,seekBar4;
    TextView tvOption1,tvOption2,tvOption3,tvOption4;
    TextView tvPercent1,tvPercent2,tvPercent3,tvPercent4;
    double count1=0,count2=0,count3=0,count4=0;
    boolean flag1=true,flag2=true,flag3=true,flag4=true;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);

        // Assign variable
        seekBar1=findViewById(R.id.seek_bar1);
        seekBar2=findViewById(R.id.seek_bar2);
        seekBar3=findViewById(R.id.seek_bar3);
        seekBar4=findViewById(R.id.seek_bar4);
        tvOption1=findViewById(R.id.tv_option1);
        tvOption2=findViewById(R.id.tv_option2);
        tvOption3=findViewById(R.id.tv_option3);
        tvOption4=findViewById(R.id.tv_option4);



        tvPercent1=findViewById(R.id.tv_percent1);
        tvPercent2=findViewById(R.id.tv_percent2);
        tvPercent3=findViewById(R.id.tv_percent3);
        tvPercent4=findViewById(R.id.tv_percent4);

        seekBar1.setProgress(0);
        seekBar2.setProgress(0);
        seekBar3.setProgress(0);
        seekBar4.setProgress(0);

       




        tvOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check condition
                if(flag1)
                {
                    resetSelection();
                    // when flag two is true
                    calculatePecent();
                    count1++;
//                    count2=0;
//                    count3=0;
//                    count4=0;
                    flag1=false;
                    flag2=true;
                    flag3=true;
                    flag4=true;
                    // calculate percentage
                    calculatePecent();
                }
            }
        });

        tvOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check condition
                if(flag2)
                {
                    resetSelection();
                    calculatePecent();
                    // when flag two is true
//                    count1=1;
                    count2++;
//                    count3=1;
//                    count4=1;
                    flag1=true;
                    flag2=false;
                    flag3=true;
                    flag4=true;
                    // calculate percentage
                    calculatePecent();
                }
            }
        });


        tvOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check condition
                if(flag3)
                {
                    resetSelection();
                    calculatePecent();
                    // when flag two is true
//                    count1=1;
//                    count2=1;
                    count3++;
//                    count4=1;
                    flag1=true;
                    flag2=true;
                    flag3=false;
                    flag4=true;
                    // calculate percentage
                    calculatePecent();
                }
            }
        });


        tvOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check condition
                if(flag4)
                {
                    resetSelection();
                    calculatePecent();
                    // when flag two is true

                    count4++;
                    flag1=true;
                    flag2=true;
                    flag3=true;
                    flag4=false;
                    // calculate percentage
                    calculatePecent();
                }
            }
        });
    }

    private void resetSelection(){
        count1=0;
        count2=0;
        count3=0;
        count4=0;
    }

    private void calculatePecent() {
        // calculate total
        double total=count1+count2+count3+count4;
        // Calculate percentage for all options
        double percent1=(count1/total)*100;
        double percent2=(count2/total)*100;
        double percent3=(count3/total)*100;
        double percent4=(count4/total)*100;
        // set percent on text view
        tvPercent1.setText(String.format("%.0f%%",percent1));
        seekBar1.setProgress((int)percent1);
        tvPercent2.setText(String.format("%.0f%%",percent2));
        seekBar2.setProgress((int)percent2);
        tvPercent3.setText(String.format("%.0f%%",percent3));
        seekBar3.setProgress((int)percent3);
        tvPercent4.setText(String.format("%.0f%%",percent4));
        seekBar4.setProgress((int)percent4);

    }
}