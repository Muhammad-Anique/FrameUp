package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.frameupclient.Model.Visitor;
import com.example.frameupclient.Model.VisitorAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitorList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_list);
        loadVisitors();
    }

    private void loadVisitors() {

//        RetrofitService retrofitService = new RetrofitService();
//        VisitorAPI visitorAPI =  retrofitService.getRetrofit().create(VisitorAPI.class);
//        visitorAPI.getPersonByRollNo("20l-2171").enqueue(new Callback<Visitor>() {
//            @Override
//            public void onResponse(Call<Person> call, Response<Visitor> response) {
//                Toast.makeText(VisitorList.this, "HEHEHEHEHEHEEHHE", Toast.LENGTH_SHORT).show();
//                System.out.println(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<Visitor> call, Throwable t) {
//                Toast.makeText(VisitorList.this, "ERRRRROORORORORORO", Toast.LENGTH_SHORT).show();
//            }
        }
}
