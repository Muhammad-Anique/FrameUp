package com.example.frameupclient.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.frameupclient.Model.ImageModel;
import com.example.frameupclient.Model.RvAdapter;
import com.example.frameupclient.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ViewPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
        ImageView img = findViewById(R.id.imgPost);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        DatabaseReference getImage = databaseReference.child("image");
        getImage.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        String link = "https://firebasestorage.googleapis.com/v0/b/frameupclientapp.appspot.com/o/1668777835243.null?alt=media&token=568850fc-95a7-4494-8320-d52f75678c3e";
                        System.out.println(link);
                        Picasso.get().load(link).into(img);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {Toast.makeText(ViewPost.this, "Error Loading Image", Toast.LENGTH_SHORT).show();}
                });

    }

}