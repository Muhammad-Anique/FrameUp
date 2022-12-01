package com.example.frameupclient.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.frameupclient.Adapter.UsersAdapter;
import com.example.frameupclient.Model.SocietyOperativeAPI;
import com.example.frameupclient.Model.SocietyParticipation;
import com.example.frameupclient.Model.SocietyParticipationAPI;
import com.example.frameupclient.Model.Users;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.example.frameupclient.databinding.ActivityUsersBinding;
import com.example.frameupclient.listeners.UserListener;
import com.example.frameupclient.utilities.Constants;
import com.example.frameupclient.utilities.PreferenceManager;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity implements UserListener {

    private ActivityUsersBinding binding;
    private PreferenceManager preferenceManager;
    private String rollNo;
    private int memType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo = extras.getString("rollNo");
        }

        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        binding = ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager  = new PreferenceManager(getApplicationContext());

//        RetrofitService retrofitService = new RetrofitService();
//        SocietyParticipationAPI societyParticipationAPI = retrofitService.getRetrofit().create(SocietyParticipationAPI.class);
//        societyParticipationAPI.getMemberExistence(rollNo).enqueue(new Callback<List<SocietyParticipation>>() {
//            @Override
//            public void onResponse(Call<List<SocietyParticipation>> call, Response<List<SocietyParticipation>> response) {
//                if(response.body()!=null){
//                    memType=1;
//                }else{
//                    System.out.println(
//                            "no list"
//                    );
//                    memType=2;
//                }
//                setListeners();
//                getUsers();
//            }
//
//            @Override
//            public void onFailure(Call<List<SocietyParticipation>> call, Throwable t) {
//
//            }
//        });

        setListeners();
        getUsers();

    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v-> onBackPressed());
    }

    private void getUsers() {
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .get()
                .addOnCompleteListener(task -> {
                    loading(false);
                    String currentUSerId =  preferenceManager.getString(Constants.KEY_USERID);
                    if(task.isSuccessful() && task.getResult() != null) {
                        List<Users> usersList = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                            if(currentUSerId.equals(queryDocumentSnapshot.getId())){
                                continue;
                            }

//                            if(memType==2){
//                            Users users = new Users();
//                            users.name = queryDocumentSnapshot.getString(Constants.KEY_NAME);
//                            users.email = queryDocumentSnapshot.getString(Constants.KEY_EMAIL);
//                            users.image = queryDocumentSnapshot.getString(Constants.KEY_IMAGE);
//                            users.token = queryDocumentSnapshot.getString(Constants.KEY_FCM_TOKEN);
//                            users.id = queryDocumentSnapshot.getId();
//                            RetrofitService retrofitService = new RetrofitService();
//                            SocietyOperativeAPI societyOperativeAPI = retrofitService.getRetrofit().create(SocietyOperativeAPI.class);
//                            societyOperativeAPI.isAdvisorByEmail(2,users.email).enqueue(new Callback<Integer>() {
//                                @Override
//                                public void onResponse(Call<Integer> call, Response<Integer> response) {
//                                    if(response.body()!=null){
//                                        if(Integer.valueOf(response.body())>0){
//                                            usersList.add(users);
//                                        }
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<Integer> call, Throwable t) {
//
//                                }
//                            });
//
//                            }
//                            else{
                                Users users = new Users();
                                users.name = queryDocumentSnapshot.getString(Constants.KEY_NAME);
                                users.email = queryDocumentSnapshot.getString(Constants.KEY_EMAIL);
                                users.image = queryDocumentSnapshot.getString(Constants.KEY_IMAGE);
                                users.token = queryDocumentSnapshot.getString(Constants.KEY_FCM_TOKEN);
                                users.id = queryDocumentSnapshot.getId();
                                usersList.add(users);
//                            }
                        }
                        if(usersList.size() > 0){
                            UsersAdapter usersAdapter = new UsersAdapter(usersList, this);
                            binding.usersRecyclerView.setAdapter(usersAdapter);
                            binding.usersRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            showErrorMessage();
                        }
                    } else {
                        showErrorMessage();
                    }
                });
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void showErrorMessage() {
        binding.textErrorMessage.setText(String.format("%s", "No user Available"));
        binding.textErrorMessage.setVisibility(View.VISIBLE);
    }

    private void loading(Boolean isLoading) {
        if(isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onUserClicked(Users users){
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra(Constants.KEY_USER, users);
        startActivity(intent);
        finish();
    }
}
