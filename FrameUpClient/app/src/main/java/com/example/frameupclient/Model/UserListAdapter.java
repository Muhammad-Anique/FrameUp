package com.example.frameupclient.Model;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class UserListHolder extends RecyclerView.ViewHolder {

    TextView userName,userEmail,userType;
    ImageView userImage;


    public UserListHolder(@NonNull View itemView) {
        super(itemView);

        userName=itemView.findViewById(R.id.name_notice_lay);
        userEmail=itemView.findViewById(R.id.email_ru_notice);
        userType=itemView.findViewById(R.id.userType_ru_lay);
        userImage=itemView.findViewById(R.id.user_image_notice);
    }
}


public class UserListAdapter extends RecyclerView.Adapter<UserListHolder>{

    List<String> memberRolls;

    public UserListAdapter(List<String> memberRolls) {
        this.memberRolls = memberRolls;
            }


    @NonNull
    @Override
    public UserListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new UserListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListHolder holder, int position) {

        String roll = memberRolls.get(position);
        System.out.println(".,.,.,.,.,.,.,.,.,.,.");
        System.out.println(roll);
        System.out.println(".,.,.,.,.,.,.,.,.,.,.");
        RetrofitService retrofitService = new RetrofitService();
        VisitorAPI visitorAPI =  retrofitService.getRetrofit().create(VisitorAPI.class);
        visitorAPI.getVisitorByRollNo(roll).enqueue(new Callback<Visitor>() {
            @Override
            public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                System.out.println(response.body());
                holder.userEmail.setText(response.body().getEmail());
                RetrofitService retrofitService = new RetrofitService();
                SocietyOperativeAPI societyOperativeAPI =  retrofitService.getRetrofit().create(SocietyOperativeAPI.class);
                societyOperativeAPI.getSocietyOperativeByRoll(response.body().getRollNo()).enqueue(new Callback<SocietyOperative>() {
                    @Override
                    public void onResponse(Call<SocietyOperative> call, Response<SocietyOperative> response) {
                        if(response.body().getOperativeType()==1){
                            holder.userType.setText("Head");
                            holder.userType.setBackgroundResource(R.drawable.orange_box);
                        }
                        else{
                            holder.userType.setText("Advisor");}
                    }

                    @Override
                    public void onFailure(Call<SocietyOperative> call, Throwable t) {
                        holder.userType.setText("Member");
                    }
                });


                holder.userName.setText(response.body().getName());
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                DatabaseReference getImage = databaseReference.child("image");
                getImage.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String link = response.body().getProfileUrl();
                                String myString = link.substring(1, link.length()-1);
                                myString=myString.replace("\\u003d","=");
                                myString=myString.replace("\\u0026","&");
                                System.out.println(myString + "hehehehehehe");
                                Picasso.get().load(myString).into(holder.userImage);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
            }

            @Override
            public void onFailure(Call<Visitor> call, Throwable t) {
                holder.userEmail.setText("Not Found");
                holder.userType.setText("Member");
                holder.userName.setText("Not found");
            }
        });

    }

    @Override
    public int getItemCount() {
        return memberRolls.size();
    }
}


