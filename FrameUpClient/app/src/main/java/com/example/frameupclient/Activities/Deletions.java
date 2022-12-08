package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.frameupclient.Model.PollAPI;
import com.example.frameupclient.Model.PostAPI;
import com.example.frameupclient.Model.Visitor;
import com.example.frameupclient.Model.VisitorAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Deletions extends AppCompatActivity {

    TextInputEditText roll, postId, st,pollID;
    Button Delroll,Delpost,Delpoll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletions);
        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        RetrofitService retrofitService =new RetrofitService();

        roll=findViewById(R.id.roll_deleteion);
        postId=findViewById(R.id.post_deletion);
        st=findViewById(R.id.status_type_admin);

        Delroll=findViewById(R.id.up_st_admin);
        Delpost=findViewById(R.id.rm_rm_rm_e_p);
        Delpoll=findViewById(R.id.remove_poll_btn);
        pollID=findViewById(R.id.poll_id_deletion);

        Delpost.setOnClickListener(view->{
            if(postId.getText().toString()==null || postId.getText().toString().isEmpty()) {
                Toast.makeText(Deletions.this, "Invalid Post or Event Id", Toast.LENGTH_SHORT).show();
            }
            else{
                PostAPI postAPI = retrofitService.getRetrofit().create(PostAPI.class);
                postAPI.deletePost(Integer.valueOf(postId.getText().toString())).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(Deletions.this, "Wait for Post/Event Deletion", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(Deletions.this, "Post/Event Failed Deletion", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Delroll.setOnClickListener(view->{
            if((roll.getText().toString()==null || roll.getText().toString().isEmpty() || st.getText().toString().isEmpty() || st.getText().toString()==null) || (st.getText().toString().compareTo("inactive")!=0 || st.getText().toString().compareTo("active")!=0) ) {
                Toast.makeText(Deletions.this, "Roll OR Status Invalid", Toast.LENGTH_SHORT).show();
            }
            else{
                VisitorAPI visitorAPI = retrofitService.getRetrofit().create(VisitorAPI.class);
                visitorAPI.updateStatusByRoll(roll.getText().toString(), st.getText().toString()).enqueue(new Callback<Visitor>() {
                    @Override
                    public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                        Toast.makeText(Deletions.this, "Status Updated", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Visitor> call, Throwable t) {
                        Toast.makeText(Deletions.this, "Failure", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Delpoll.setOnClickListener(view->{
            if(pollID.getText().toString()==null || pollID.getText().toString().isEmpty()) {
                Toast.makeText(Deletions.this, "Invalid PollId", Toast.LENGTH_SHORT).show();
            }else{
                PollAPI pollAPI = retrofitService.getRetrofit().create(PollAPI.class);
                pollAPI.deletePoll(Integer.valueOf(pollID.getText().toString())).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(Deletions.this, "Wait for Poll Deletion", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(Deletions.this, "Poll Deletion Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}