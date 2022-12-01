package com.example.frameupclient.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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



public class CreatePost extends AppCompatActivity   {

    ImageView cover;
    Button uploadBtn;
    ProgressBar progressbar;
    Uri uri;
    String path;
//    String[] items =  {"General","IEEE","Softec","Career Counselling","Drama Society","Music society"};
//    AutoCompleteTextView autoCompleteTxt;
//    ArrayAdapter<String> adapterItems;
    
    //data for post
    String PostType;
    String PostSubject;
    String rollNo;
    String AuthorRoll;
    String Caption;
    String MediaUrl;
    String Hashtag;
    int Priority;
    String Date;
    boolean imageUploaded = false;
    int sid;



    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo = extras.getString("rollNo");
            sid = extras.getInt("sid");
        }
        TextView postError = findViewById(R.id.post_error);

        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        cover = findViewById(R.id.img);
        progressbar =findViewById(R.id.progressbar);
        uploadBtn= findViewById(R.id.create_post_btn);

//        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
//
//        adapterItems = new ArrayAdapter<String>(this,R.layout.droplist_item,items);
//        autoCompleteTxt.setAdapter(adapterItems);
//
//
//        //listners
//        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                SocietyAssociated = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Item: "+SocietyAssociated,Toast.LENGTH_SHORT).show();
//            }
//        });


        progressbar.setVisibility(View.INVISIBLE);


        cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(CreatePost.this)
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
                    Toast.makeText(CreatePost.this, "Please Select Image", Toast.LENGTH_SHORT).show();
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


    public void uploadPost()
    {
        TextInputEditText caption_tf = findViewById(R.id.Post_Caption);
        TextInputEditText subject_tf = findViewById(R.id.Post_subject);
        TextInputEditText hashtag_tf = findViewById(R.id.Post_hashtag);
        TextView postError = findViewById(R.id.post_error);

        RetrofitService retrofitService = new RetrofitService();
        PostAPI postAPI =  retrofitService.getRetrofit().create(PostAPI.class);

        Caption =String.valueOf(caption_tf.getText());
        PostSubject =String.valueOf(subject_tf.getText());
        Hashtag = String.valueOf(hashtag_tf.getText());
        PostType = "General";
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date = df.format(c);
        Priority = 1;
        String roll ="20l-2179";
        AuthorRoll= String.valueOf(roll);
        Post p = new Post();

        p.setPostSubject(PostSubject);
        p.setPostText(Caption);
        p.setPriority(1);
        p.setPostCreationDate(Date);
        p.setLink(MediaUrl);
        p.setHashtag(Hashtag);
        p.setSocietyAssociated(String.valueOf(sid));
        p.setPostType(PostType);
        p.setAuthorRoll(rollNo);

        System.out.println(p);

        if(Caption!=null && PostSubject!=null && imageUploaded) {
            postError.setText("Wait for Server Response");
            postAPI.save(p).enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    postError.setText("Successfully Uploaded");
                }
                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    postError.setText("Server Down");
                }
            });
        }else{
            postError.setText("Caption, Image And Subject Cannot Be Null");

        }


    }

    private void uploadToFirebase(Uri uri){

        TextView postError = findViewById(R.id.post_error);
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
                        MediaUrl = String.valueOf(model.getImageUrl().toString());
                        System.out.println(model.getImageUrl());
                        System.out.println("********");
                        root.child(modelId).setValue(model);
                        progressbar.setVisibility(View.INVISIBLE);
                        Toast.makeText(CreatePost.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        cover.setImageResource(R.drawable.image_icon);
                        imageUploaded=true;
                        postError.setText("image is uploaded wait for data to upload");
                        uploadPost();
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
                Toast.makeText(CreatePost.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }


}