package com.example.frameupclient.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frameupclient.Adapter.UsersAdapter;
import com.example.frameupclient.Model.Users;
import com.example.frameupclient.databinding.ActivityUsersBinding;
import com.example.frameupclient.listeners.UserListener;
import com.example.frameupclient.utilities.Constants;
import com.example.frameupclient.utilities.PreferenceManager;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity implements UserListener {

    private ActivityUsersBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_users);
        binding = ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager  = new PreferenceManager(getApplicationContext());
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
                            Users users = new Users();
                            users.name = queryDocumentSnapshot.getString(Constants.KEY_NAME);
                            users.email = queryDocumentSnapshot.getString(Constants.KEY_EMAIL);
                            users.image = queryDocumentSnapshot.getString(Constants.KEY_IMAGE);
                            users.token = queryDocumentSnapshot.getString(Constants.KEY_FCM_TOKEN);
                            users.id = queryDocumentSnapshot.getId();
                            usersList.add(users);
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
