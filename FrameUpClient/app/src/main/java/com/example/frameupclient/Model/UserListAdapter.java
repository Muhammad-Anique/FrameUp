package com.example.frameupclient.Model;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frameupclient.Activities.SocietyRecyclerViewInterface;
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

        userName=itemView.findViewById(R.id.name_ru_lay);
        userEmail=itemView.findViewById(R.id.email_ru_lay);
        userType=itemView.findViewById(R.id.userType_ru_lay);
        userImage=itemView.findViewById(R.id.user_image_ru);
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
                holder.userType.setText("head");
                holder.userName.setText(response.body().getName());
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                DatabaseReference getImage = databaseReference.child("image");
                getImage.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String link = response.body().getProfileUrl();
                                System.out.println(link);
                                Picasso.get().load(link).into(holder.userImage);
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


