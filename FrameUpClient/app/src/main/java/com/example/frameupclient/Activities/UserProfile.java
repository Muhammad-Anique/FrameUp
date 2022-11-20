package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.frameupclient.R;

public class UserProfile extends Fragment {

    boolean btnstate =false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btn =getView().findViewById(R.id.heart_button_profile);
        btn.setOnClickListener(view -> {
            if(!btnstate){
            btn.setBackgroundResource(R.drawable.heart_clicked);
            btnstate=true;
            }
            else{
                btn.setBackgroundResource(R.drawable.heart_notclicked);
                btnstate=false;
            }
        });

        return inflater.inflate(R.layout.activity_user_profile,container,false);
    }

}