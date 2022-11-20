package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.frameupclient.Model.Post;
import com.example.frameupclient.Model.PostAPI;
import com.example.frameupclient.Model.RvAdapter;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitorHome extends AppCompatActivity {

    public String rollNo;
    RecyclerView rvMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_home);

        Window window =this.getWindow();

        Button profile_btn= findViewById(R.id.visitor_home_profile_button);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo = extras.getString("userRoll");
        }

      profile_btn.setOnClickListener(view->{
          Intent intent = new Intent(this, UserProfile.class);
          intent.putExtra("userRoll", rollNo);
          startActivity(intent);
      });


// clear FLAG_TRANSLUCENT_STATUS flag:
       window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        rvMain = findViewById(R.id.visitor_home_page_recycler_view);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        loadPosts();
    }

    private void loadPosts() {
        RetrofitService retrofitService = new RetrofitService();
        PostAPI postApi =  retrofitService.getRetrofit().create(PostAPI.class);
        postApi.getAllPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                populate(response.body());
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    public void populate(List<Post> p){

        RvAdapter rvAdapter = new RvAdapter(p);
        rvMain.setAdapter(rvAdapter);
    }
}