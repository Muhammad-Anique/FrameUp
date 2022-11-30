package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.frameupclient.Model.Poll;
import com.example.frameupclient.Model.PollAPI;
import com.example.frameupclient.Model.PollAdapter;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPoll extends AppCompatActivity implements PollRecyclerViewInterface {

    List<Poll> p;
    RecyclerView R1;
    String rollNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_poll);
        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        R1 =findViewById(R.id.poll_list_view_recycler);
        R1.setLayoutManager(new LinearLayoutManager(this));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo = extras.getString("rollNo");
        }
        loadpolls();

    }

    private void loadpolls() {

        RetrofitService retrofitService = new RetrofitService();
        PollAPI pollAPI = retrofitService.getRetrofit().create(PollAPI.class);
        pollAPI.getAllPoll().enqueue(new Callback<List<Poll>>() {
            @Override
            public void onResponse(Call<List<Poll>> call, Response<List<Poll>> response) {
                populatePollList(response.body());
                p=response.body();
            }

            @Override
            public void onFailure(Call<List<Poll>> call, Throwable t) {

            }
        });
    }

    private void populatePollList(List<Poll> polls) {
        PollAdapter pollAdapter =new PollAdapter(polls,this);
        R1.setAdapter(pollAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, PollStructure.class);
        Poll polly = p.get(position);
        intent.putExtra("pollId",polly.getPollId());
        intent.putExtra("rollNo",rollNo);
        startActivity(intent);
    }
}