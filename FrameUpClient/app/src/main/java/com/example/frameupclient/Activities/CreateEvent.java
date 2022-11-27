package com.example.frameupclient.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frameupclient.Model.ImageModel;
import com.example.frameupclient.Model.Post;
import com.example.frameupclient.Model.PostAPI;
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

public class CreateEvent extends AppCompatActivity {

    TextInputEditText e_caption, e_start_date,e_start_time,e_end_time,e_event_type,e_venue;
    Button create_event;
    ProgressBar progressbar;
    Uri uri;
    String MediaUrl;
    ImageView cover;

    TextView eventError;
    String eDate ;
    String eSTime ;
    String eETime ;
    String eCap;
    String venue;
    String type ;

    String rollNo;
    String sid;
    boolean imageUploaded=false;
    RetrofitService retrofitService;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
         retrofitService = new RetrofitService();
        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        intializeComponents();
    }

    private void intializeComponents() {
        e_caption=findViewById(R.id.Event_Caption);
        e_start_date=findViewById(R.id.Event_Date);
        e_end_time=findViewById(R.id.Event_time_end);
        e_start_time=findViewById(R.id.Event_time_start);
        e_venue=findViewById(R.id.Event_Venue);
        e_event_type=findViewById(R.id.Event_Type);
        progressbar=findViewById(R.id.progressbar_ee);
        create_event=findViewById(R.id.create_event_btn);
        eventError=findViewById(R.id.event_error);
        cover=findViewById(R.id.event_bg_img);
        progressbar.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo = extras.getString("rollNo");
            sid = extras.getString("sid");

        }
        cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(CreateEvent.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080,1080)
                        .start(101);

            }
        });

        create_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (uri != null){
                    uploadToFirebase(uri);
                }else{
                    Toast.makeText(CreateEvent.this, "Please Select Image", Toast.LENGTH_SHORT).show();
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


    public void createEvent()
    {
        eDate = String.valueOf(e_start_date.getText());
        eSTime = String.valueOf(e_start_time.getText());
        eETime = String.valueOf(e_end_time.getText());
        eCap = String.valueOf(e_caption.getText());
        venue = String.valueOf(e_venue.getText());
        type = String.valueOf(e_event_type.getText());
        int priority = 2;
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String date = df.format(c);
        String roll ="20l-2179";
        String AuthorRoll= String.valueOf(roll);

        PostAPI postAPI = retrofitService.getRetrofit().create(PostAPI.class);
        Post post = new Post();

        post.setEventDate(eDate);
        post.setEventType(type);
        post.setEventStartTime(eSTime);
        post.setAuthorRoll(rollNo);
        post.setHashtag(null);
        post.setLink(MediaUrl);
        post.setPriority(2);
        post.setEventVenue(venue);
        post.setEventEndTime(eETime);
        post.setPostCreationDate(date);
        post.setPostText(eCap);
        post.setSocietyAssociated(sid);


        System.out.println(post);

        if(eCap!=null && venue!=null && imageUploaded) {
            eventError.setText("Wait for Server Response");
            postAPI.save(post).enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    eventError.setText("Successfully Uploaded");
                }
                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                   eventError.setText("Server Down");
                }
            });
        }else{
           eventError.setText("Caption, Image And Subject Cannot Be Null");

        }


    }


    private void uploadToFirebase(Uri uri){
        eventError.setText("wait for image to upload");
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
                        progressbar.setVisibility(View.INVISIBLE);
                        Toast.makeText(CreateEvent.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        cover.setImageResource(R.drawable.gradient_grey);
                        eventError.setText("image is uploaded wait for data to upload");
                        imageUploaded=true;
                        createEvent();
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
                Toast.makeText(CreateEvent.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }
}