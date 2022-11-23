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
import com.example.frameupclient.Model.Society;
import com.example.frameupclient.Model.SocietyAPI;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateSociety extends AppCompatActivity {

    TextInputEditText society_name, society_head, society_tagline,society_description;
    String society_name_s, head_s,tagline_s,descrip_s;
    ImageView societyBackground;
    ProgressBar progressBar;
    Button upBtn;
    String MediaUrl;
    TextView societyError;
    Uri uri;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_society);
        intializeComponents();
    }

    private void intializeComponents() {
        society_name =findViewById(R.id.Society_Name);
        society_description= findViewById(R.id.Society_Description);
        society_head=findViewById(R.id.Society_Head_Roll);
        society_tagline=findViewById(R.id.Society_Motive);
        societyBackground = findViewById(R.id.Society_Back_Img);
        progressBar=findViewById(R.id.progressbar_society_create);
        upBtn=findViewById(R.id.create_society_btn);
        societyError=findViewById(R.id.Society_error);
        progressBar.setVisibility(View.INVISIBLE);
        societyBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(CreateSociety.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080,1080)
                        .start(101);

            }
        });

        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (uri != null){
                    uploadToFirebase(uri);
                }else{
                    Toast.makeText(getApplicationContext(),"Select image",Toast.LENGTH_SHORT).show();
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
            societyBackground.setImageURI(uri);

        }else{
            Toast.makeText(getApplicationContext(),"no image",Toast.LENGTH_SHORT).show();
        }
    }



    public void uploadSociety()
    {

        RetrofitService retrofitService = new RetrofitService();
        SocietyAPI societyAPI =  retrofitService.getRetrofit().create(SocietyAPI.class);
        societyError=findViewById(R.id.Society_error);
        society_name_s = String.valueOf(society_name.getText());
        head_s=String.valueOf(society_head.getText());
        tagline_s=String.valueOf(society_tagline.getText());
        descrip_s=String.valueOf(society_description.getText());
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String Date2 = df.format(c);


        Society society =new Society();
        society.setSocietyHead(head_s);
        society.setSocietyLikes(0);
        society.setSocietyDescription(descrip_s);
        society.setSocietyName(society_name_s);
        society.setSocietyTagline(tagline_s);
        society.setSocietyRating(0);
        society.setSocietyBackground(MediaUrl);
        society.setDateCreated(Date2);

        if(head_s!=null && society_name_s!=null) {
            societyAPI.save(society).enqueue(new Callback<Society>() {
                @Override
                public void onResponse(Call<Society> call, Response<Society> response) {
                    Toast.makeText(CreateSociety.this, "Society created", Toast.LENGTH_SHORT).show();
                    societyError.setText("Uploaded");
                }

                @Override
                public void onFailure(Call<Society> call, Throwable t) {
                    Toast.makeText(CreateSociety.this, "Server down", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(CreateSociety.this, "Head and Name connot be Null", Toast.LENGTH_SHORT).show();
        }

    }


    private void uploadToFirebase(Uri uri){

        TextView Error = findViewById(R.id.Society_error);
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
                        MediaUrl = String.valueOf(model.getImageUrl().toString());
                        System.out.println(model.getImageUrl());
                        System.out.println("********");
                        root.child(modelId).setValue(model);
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(CreateSociety.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        societyBackground.setImageResource(R.drawable.gradient_grey);
                        Error.setText("image is uploaded wait for data to upload");
                        uploadSociety();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(CreateSociety.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }

}