package com.example.frameupclient.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frameupclient.Model.ImageModel;
import com.example.frameupclient.Model.Post;
import com.example.frameupclient.Model.PostAPI;
import com.example.frameupclient.Model.Visitor;
import com.example.frameupclient.Model.VisitorAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfile extends AppCompatActivity {

    public String rollNo;
    public Button uploadBtn;
    public ProgressBar progressbar;
    public Uri uri;
    public String profilePicUrl;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    public ImageView cover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        TextView name = findViewById(R.id.profile_name);
        TextView email =findViewById(R.id.profile_email);
        Button profile_btn= findViewById(R.id.visitor_profile_page_profile_button);///


        uploadBtn = findViewById(R.id.upload_profile_pic);
        progressbar =findViewById(R.id.progressBar2);
        cover = findViewById(R.id.profile_pic);
        progressbar.setVisibility(View.INVISIBLE);
        RetrofitService retrofitService = new RetrofitService();
        VisitorAPI visitorAPI =  retrofitService.getRetrofit().create(VisitorAPI.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo =extras.getString("userRoll");

            visitorAPI.getVisitorByRollNo(rollNo).enqueue(new Callback<Visitor>() {
                @Override
                public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                name.setText(response.body().getName());
                email.setText(response.body().getEmail());


                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference();
                    DatabaseReference getImage = databaseReference.child("image");
                    getImage.addListenerForSingleValueEvent(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String link = response.body().getProfileUrl();
                                    System.out.println(link);
                                    if(link!=null) {
                                        String myString = link.substring(1, link.length()-1);
                                        Picasso.get().load(myString).into(cover);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });


                }

                @Override
                public void onFailure(Call<Visitor> call, Throwable t) {
                    name.setText("Error loading Profile");

                }
            });

        }

        cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(UserProfile.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080,1080)
                        .start(101);

            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (uri != null){
                    uploadToFirebase(uri);
                }else{
                    Toast.makeText(UserProfile.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101 && resultCode == Activity.RESULT_OK)
        {
            uri = data.getData();
            System.out.println(uri);
            cover.setImageURI(uri);

        }else{
            Toast.makeText(getApplicationContext(),"no image",Toast.LENGTH_SHORT).show();
        }
    }


    private void uploadToFirebase(Uri uri){

        TextView postError = findViewById(R.id.profile_extra_info);
        postError.setText("wait for image to upload");
        final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        ImageModel model = new ImageModel(uri.toString());
                        String modelId = root.push().getKey();
                        System.out.println(modelId);
                        System.out.println(model);
                        profilePicUrl = String.valueOf(model.getImageUrl().toString());
                        System.out.println(model.getImageUrl());
                        System.out.println("********");
                        root.child(modelId).setValue(model);
                        progressbar.setVisibility(View.INVISIBLE);
                        uploadToDataBase();
                        Toast.makeText(UserProfile.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        cover.setImageResource(R.drawable.image_icon);
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressbar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressbar.setVisibility(View.INVISIBLE);
                Toast.makeText(UserProfile.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }

    public boolean uploadToDataBase()
    {
        TextView postError = findViewById(R.id.profile_extra_info);
        RetrofitService retrofitService = new RetrofitService();
        VisitorAPI visitorAPI =  retrofitService.getRetrofit().create(VisitorAPI.class);
        visitorAPI.updateProfilePic(rollNo, profilePicUrl).enqueue(new Callback<Visitor>() {
            @Override
            public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                postError.setText("Uploaded to Database");
            }

            @Override
            public void onFailure(Call<Visitor> call, Throwable t) {
                postError.setText("Error in uploading to Database");
            }
        });

        return true;
    }


}