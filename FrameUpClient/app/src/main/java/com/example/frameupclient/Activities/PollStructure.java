package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frameupclient.Model.Poll;
import com.example.frameupclient.Model.PollAPI;
import com.example.frameupclient.Model.PollResponded;
import com.example.frameupclient.Model.PollRespondedAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PollStructure extends AppCompatActivity {

    // Initialize variable
    SeekBar seekBar1,seekBar2,seekBar3,seekBar4;
    TextView tvOption1,tvOption2,tvOption3,tvOption4,pollHeading;
    TextView tvPercent1,tvPercent2,tvPercent3,tvPercent4,dialogMsg;
    ProgressBar progress;
    int count1=0,count2=0,count3=0,count4=0;
    Button submit;
    boolean flag1=true,flag2=true,flag3=true,flag4=true;
    String qq;
    Poll poll;
    int PollId;
    String rollNo;
    Dialog d;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_structure);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
          PollId=extras.getInt("pollId");
          rollNo=extras.getString("rollNo");
          System.out.println(PollId);;
        }


        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        RetrofitService retrofitService3 = new RetrofitService();
        PollRespondedAPI pollRespondedAPI =  retrofitService3.getRetrofit().create(PollRespondedAPI.class);
        pollRespondedAPI.getResponseExistence(rollNo).enqueue(new Callback<PollResponded>() {
            @Override
            public void onResponse(Call<PollResponded> call, Response<PollResponded> response) {
                if(response.body()!=null){
                if(response.body().getPollId()==PollId)
                    submit.setVisibility(View.INVISIBLE);
                else{
                    submit.setVisibility(View.VISIBLE);
                }}
            }
            @Override
            public void onFailure(Call<PollResponded> call, Throwable t) {
                submit.setVisibility(View.VISIBLE);
            }
        });


        d=new Dialog(this);
        d.setContentView(R.layout.custom_congratulate_dialogue);
        d.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogMsg =findViewById(R.id.custom_dialogue_description);
         pollHeading =findViewById(R.id.tv_question);
                 progress=findViewById(R.id.progressBar_poll);

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

        progress.setVisibility(View.INVISIBLE);


        RetrofitService retrofitService = new RetrofitService();
        PollAPI pollAPI = retrofitService.getRetrofit().create(PollAPI.class);
        System.out.println(PollId + "]]]]]]");
        pollAPI.getPollById(PollId).enqueue(new Callback<Poll>() {
            @Override
            public void onResponse(Call<Poll> call, Response<Poll> response) {
                poll=response.body();
                System.out.println(poll);
                IntializePoll();
            }

            @Override
            public void onFailure(Call<Poll> call, Throwable t) {

            }
        });




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
                    count2++;
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

        submit=findViewById(R.id.submit_response);
        submit.setOnClickListener(view->{

            progress.setVisibility(View.VISIBLE);
            RetrofitService retrofitService2 = new RetrofitService();
            PollAPI pollAPI2 = retrofitService2.getRetrofit().create(PollAPI.class);
            System.out.println(PollId + "]]]]]]");
            poll.setOption1Responses(count1);
            poll.setOption2Responses(count2);
            poll.setOption3Responses(count3);
            poll.setOption4Responses(count4);
            poll.setNoOfResponses(count1+count2+count3+count4);
            pollAPI2.addPollResponse(poll.getPollId(),poll).enqueue(new Callback<Poll>() {
                @Override
                public void onResponse(Call<Poll> call, Response<Poll> response) {
                    PollResponded pollResponded = new PollResponded();
                    pollResponded.setPollId(PollId);
                    pollResponded.setRollNo(rollNo);
                    PollRespondedAPI pollRespondedAPI =  retrofitService2.getRetrofit().create(PollRespondedAPI.class);
                    pollRespondedAPI.save(pollResponded).enqueue(new Callback<PollResponded>() {
                        @Override
                        public void onResponse(Call<PollResponded> call, Response<PollResponded> response) {
                            progress.setVisibility(View.INVISIBLE);
                            submit.setVisibility(View.INVISIBLE);
                            Toast.makeText(PollStructure.this, "Poll Response Submitted", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<PollResponded> call, Throwable t) {

                        }
                    });



                }

                @Override
                public void onFailure(Call<Poll> call, Throwable t) {

                }
            });

        });


    }

    private void IntializePoll() {
        pollHeading.setText(poll.getPollStatement());

        if(poll.getPollOption1()!=null){
            tvOption1.setText(poll.getPollOption1());
        }else{
            tvOption1.setVisibility(View.INVISIBLE);
            seekBar1.setVisibility(View.INVISIBLE);
            tvPercent1.setVisibility(View.INVISIBLE);
        }

        if(poll.getPollOption2()!=null){
            tvOption2.setText(poll.getPollOption2());
        }else{
            tvOption2.setVisibility(View.INVISIBLE);
            seekBar2.setVisibility(View.INVISIBLE);
            tvPercent2.setVisibility(View.INVISIBLE);
        }

        if(poll.getPollOption3()!=null){
            tvOption3.setText(poll.getPollOption3());
        }else{
            tvOption3.setVisibility(View.INVISIBLE);
            seekBar3.setVisibility(View.INVISIBLE);
            tvPercent3.setVisibility(View.INVISIBLE);
        }

        if(poll.getPollOption4()!=null){
            tvOption4.setText(poll.getPollOption4());
        }else if(poll.getPollOption4()==null){
            tvOption4.setVisibility(View.INVISIBLE);
            seekBar4.setVisibility(View.INVISIBLE);
            tvPercent4.setVisibility(View.INVISIBLE);
        }

        count1 =poll.getOption1Responses();
        count2 =poll.getOption2Responses();
        count3 =poll.getOption3Responses();
        count4 =poll.getOption4Responses();

        calculatePecent();
    }

    private void resetSelection(){
        count1 =poll.getOption1Responses();
        count2 =poll.getOption2Responses();
        count3 =poll.getOption3Responses();
        count4 =poll.getOption4Responses();
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