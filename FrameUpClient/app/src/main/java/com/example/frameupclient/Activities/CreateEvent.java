package com.example.frameupclient.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.frameupclient.R;
import com.google.android.material.textfield.TextInputEditText;

public class CreateEvent extends AppCompatActivity {

    TextInputEditText e_caption, e_start_date,e_start_time,e_end_time,e_event_type,e_venue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        intializeComponents();
    }

    private void intializeComponents() {
    }
}