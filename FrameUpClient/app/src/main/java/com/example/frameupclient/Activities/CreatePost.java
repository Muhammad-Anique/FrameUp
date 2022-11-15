package com.example.frameupclient.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.frameupclient.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class CreatePost extends AppCompatActivity {

    ImageView cover;
    Button btn;
    Uri uri;
    String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        cover = findViewById(R.id.img);
        btn = findViewById(R.id.btng);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(CreatePost.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080,1080)
                        .start(101);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101 && resultCode == Activity.RESULT_OK)
        {
            uri = data.getData();
            cover.setImageURI(uri);

        }else{
            Toast.makeText(getApplicationContext(),"no image",Toast.LENGTH_SHORT).show();
        }
    }
}