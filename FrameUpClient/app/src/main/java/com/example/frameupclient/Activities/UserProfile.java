package com.example.frameupclient.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.frameupclient.utilities.Constants;
import com.example.frameupclient.utilities.PreferenceManager;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfile extends AppCompatActivity {

    public String rollNo;
    public String fireBaseEmail;
    public String fireBasePassword;
    public String fireBaseName;
    public String fireBaseEncodedImage;

    private PreferenceManager preferenceManager;

    public Button uploadBtn;
    public ProgressBar progressbar;
    public Uri uri;
    public String profilePicUrl;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    public ImageView cover;
    Intent intent_home;

    Button profile_btn;
    Button home_btn;
    Button society_btn;
    Button report_user;

    TextView eip;

    ConstraintLayout nv;

    boolean profilePicUploaded =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

      intent_home = new Intent(this, VisitorHome.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        preferenceManager = new PreferenceManager(getApplicationContext());
        TextView name = findViewById(R.id.profile_name);
        TextView email =findViewById(R.id.profile_email);

        report_user=findViewById(R.id.report_user_in_profile);
        nv=findViewById(R.id.nv_in_user_profile);
        eip=findViewById(R.id.error_in_user_profile);


        profile_btn =findViewById(R.id.profile_button_up);
        profile_btn.setBackgroundTintList(this.getColorStateList((R.color.Primary_Color_2)));

        society_btn =findViewById(R.id.society_button_up);
        society_btn.setOnClickListener(view->{
            Intent intent = new Intent(this, ViewSociety.class);
            intent.putExtra("userRoll",rollNo);
            startActivity(intent);

        });

        report_user.setOnClickListener(view->{
            Intent intent = new Intent(this, CreateNotice.class);
            intent.putExtra("userRoll",rollNo);
            startActivity(intent);
        });

        home_btn =findViewById(R.id.home_button_up);
        home_btn.setOnClickListener(view->{
            Intent intent = new Intent(this, VisitorHome.class);
            intent.putExtra("userRoll",rollNo);
            startActivity(intent);
        });

        Window window =this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));
        window.setNavigationBarColor(ContextCompat.getColor(this,R.color.Primary_Color_1));




        uploadBtn = findViewById(R.id.upload_profile_pic);
        progressbar =findViewById(R.id.progressBar2);
        cover = findViewById(R.id.profile_pic);
        progressbar.setVisibility(View.INVISIBLE);




        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollNo =extras.getString("userRoll");
        }





        RetrofitService retrofitService = new RetrofitService();
        VisitorAPI visitorAPI =  retrofitService.getRetrofit().create(VisitorAPI.class);
        visitorAPI.getVisitorByRollNo(rollNo).enqueue(new Callback<Visitor>() {
            @Override
            public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                name.setText(response.body().getName());
                email.setText(response.body().getEmail());
                if(response.body().getProfileUrl()==null)
                    profilePicUploaded=false;
                else
                    profilePicUploaded=true;
                if(!profilePicUploaded){
                    nv.setVisibility(View.INVISIBLE);
                    eip.setText("Upload Profile pic to complete Registeration process");
                }
            }

            @Override
            public void onFailure(Call<Visitor> call, Throwable t) {
                profilePicUploaded=false;
                name.setText("Error loading Profile");

            }
        });


            visitorAPI.getVisitorByRollNo(rollNo).enqueue(new Callback<Visitor>() {
                @Override
                public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                fireBaseEmail =response.body().getEmail();
                fireBaseName = response.body().getName();
                fireBasePassword =response.body().getPassword();

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
                                        myString=myString.replace("\\u003d","=");
                                        myString=myString.replace("\\u0026","&");
                                        System.out.println(myString + "hehehehehehe");
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

            Uri ImageUri = data.getData();
            System.out.println(ImageUri);
            try{
                InputStream inputStream = getContentResolver().openInputStream(ImageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                fireBaseEncodedImage = encodeImage(bitmap);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }


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
                        System.out.println(model.getImageUrl());
                        profilePicUrl = model.getImageUrl();
                        System.out.println(profilePicUrl);
                        System.out.println("********");
                        root.child(modelId).setValue(model);
                        progressbar.setVisibility(View.INVISIBLE);
                        uploadToDataBase();
                        Toast.makeText(UserProfile.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();

                        System.out.println("FireBase");
                        System.out.println(uri);
                        System.out.println("FireBase");

                        if(!profilePicUploaded) {
                            signup();
                            finish();
                            intent_home.putExtra("userRoll",rollNo);
                            startActivity(intent_home);

                        }


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



    private void signup() {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String, Object> user = new HashMap<>();
        user.put(Constants.KEY_NAME, fireBaseName);
        user.put(Constants.KEY_EMAIL,fireBaseEmail);
        user.put(Constants.KEY_PASSWORD, fireBasePassword);
        user.put(Constants.KEY_IMAGE, fireBaseEncodedImage);
        database.collection(Constants.KEY_COLLECTION_USERS)
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                    preferenceManager.putString(Constants.KEY_USERID, documentReference.getId());
                    preferenceManager.putString(Constants.KEY_NAME, fireBaseName);
                    preferenceManager.putString(Constants.KEY_IMAGE,fireBaseEncodedImage);
                })
                .addOnFailureListener(exception -> {

                });

        signIn();
    }


    private void signIn(){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .whereEqualTo(Constants.KEY_EMAIL, fireBaseEmail)
                .whereEqualTo(Constants.KEY_PASSWORD,fireBasePassword)
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult() != null && task.getResult().getDocuments().size()>0){
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                        preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                        preferenceManager.putString(Constants.KEY_USERID, documentSnapshot.getId());
                        preferenceManager.putString(Constants.KEY_NAME, documentSnapshot.getString(Constants.KEY_NAME));
                        preferenceManager.putString(Constants.KEY_IMAGE, documentSnapshot.getString(Constants.KEY_IMAGE));
                    } else{

                    }
                });
    }


    private String encodeImage(Bitmap bitmap){
        int previewWidth = 150;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.getEncoder().encodeToString(bytes);
    }
}