package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_poll);
        R1 =findViewById(R.id.poll_list_view_recycler);
        R1.setLayoutManager(new LinearLayoutManager(this));
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
        intent.putExtra("statement",polly.getPollStatement());
        intent.putExtra("pollid",polly.getPollId());
        startActivity(intent);
    }
}