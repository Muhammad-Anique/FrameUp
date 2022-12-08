package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.frameupclient.Model.Poll;
import com.example.frameupclient.Model.PollAPI;
import com.example.frameupclient.Model.PostAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePoll extends AppCompatActivity {

    CheckBox checkBox1,checkBox2,checkBox3,checkBox4;
    Button uploadBtn;
    String rollNo;
    TextInputEditText caption, option1, option2, option3,option4;
    boolean O1=true,O2=true,O3=true,O4=true;
    ProgressBar prg;
    int count=5;
    int sid;

    void set_count_down(){
        if(count>0)
            count--;
    }

    void set_count_up(){
            count++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);
        Window window =this.getWindow();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo = extras.getString("rollNo");
            sid = extras.getInt("sid");
        }
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        intializeComponents();
    }

    private void intializeComponents() {

        TextView poll_error = findViewById(R.id.poll_info);
        prg =findViewById(R.id.progressBar_poll);
        prg.setVisibility(View.INVISIBLE);
        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);

        caption = findViewById(R.id.Poll_Caption);
        option1 = findViewById(R.id.Poll_Option1_tf);
        option2 = findViewById(R.id.Poll_Option2_tf);
        option3 = findViewById(R.id.Poll_Option3_tf);
        option4 = findViewById(R.id.Poll_Option4_tf);

        uploadBtn = findViewById(R.id.Submit_poll_btn);

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(!checkBox1.isChecked())
                {
                    option1.setVisibility(View.INVISIBLE);
                    O1=false;
                    set_count_down();
                }else{
                    option1.setVisibility(View.VISIBLE);
                    O1=true;
                    set_count_up();
                }
            }
        });


        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(!checkBox2.isChecked())
                {   option2.setVisibility(View.INVISIBLE);
                    O2=false;
                    set_count_down();
                }else{
                    option2.setVisibility(View.VISIBLE);
                    O2=true;
                    set_count_up();
                }
            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(!checkBox3.isChecked())
                {   option3.setVisibility(View.INVISIBLE);
                    O3=false;
                    set_count_down();
                }else{
                    option3.setVisibility(View.VISIBLE);
                    O3=true;
                    set_count_up();
                }
            }
        });


        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(!checkBox4.isChecked())
                {   option4.setVisibility(View.INVISIBLE);
                    O4=false;
                    set_count_down();
                }else{
                    option4.setVisibility(View.VISIBLE);
                    O4=true;
                    set_count_up();
                }
            }
        });



        uploadBtn.setOnClickListener(view->{
            prg.setVisibility(View.VISIBLE);
            String cap = String.valueOf(caption.getText());
            String opt1 = String.valueOf(option1.getText());
            String opt2 = String.valueOf(option2.getText());
            String opt3 = String.valueOf(option3.getText());
            String opt4 = String.valueOf(option4.getText());


            if(cap==null || cap.isEmpty() || ((opt1 == null || opt1.isEmpty()) && (opt2==null || opt2.isEmpty()) && (opt3==null || opt3.isEmpty()) && (opt4==null || opt4.isEmpty()))){
                poll_error.setText("Poll Cannot be Uploaded Missing values");
            }
            else {

                if (count < 2) {
                    poll_error.setText("Select Atleast two options");
                } else {
                    Poll poll = new Poll();
                    poll.setCreatedBy(rollNo);
                    poll.setPollStatement(cap);
                    poll.setSocietyRelated(String.valueOf(sid));
                    poll.setNoOfResponses(0);
                    poll.setTotalOptions(count);
                    if (O1)
                        poll.setPollOption1(opt1);
                    poll.setOption1Responses(0);
                    if (O2)
                        poll.setPollOption2(opt2);
                    poll.setOption2Responses(0);
                    if (O3)
                        poll.setPollOption3(opt3);
                    poll.setOption3Responses(0);
                    if (O4)
                        poll.setPollOption4(opt4);
                    poll.setOption4Responses(0);
                    RetrofitService retrofitService = new RetrofitService();
                    PollAPI pollAPI = retrofitService.getRetrofit().create(PollAPI.class);
                    pollAPI.save(poll).enqueue(new Callback<Poll>() {
                        @Override
                        public void onResponse(Call<Poll> call, Response<Poll> response) {
                            poll_error.setText("Poll is Uploaded");
                            prg.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onFailure(Call<Poll> call, Throwable t) {
                            poll_error.setText("Server is Down");
                        }
                    });
                }

            }
        });

    }
}