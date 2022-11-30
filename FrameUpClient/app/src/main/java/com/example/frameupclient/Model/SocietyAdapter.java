package com.example.frameupclient.Model;

import android.icu.number.Precision;
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

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class SocietyHolder extends RecyclerView.ViewHolder {

    TextView noofmembers, society_rating, society_tagline, society_name, society_badge;
    ImageView imageView;

    public SocietyHolder(@NonNull View itemView, SocietyRecyclerViewInterface societyRecyclerViewInterface) {
        super(itemView);

        noofmembers =itemView.findViewById(R.id.noof_member_row_society);
        society_rating=itemView.findViewById(R.id.rating_row_society);
        society_tagline=itemView.findViewById(R.id.row_society_tagline);
        society_name = itemView.findViewById(R.id.society_statement_in_view);
        society_badge = itemView.findViewById(R.id.society_badge);
        imageView=itemView.findViewById(R.id.society_background_image_row_society);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(societyRecyclerViewInterface!=null)
                {
                    int position = getBindingAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION)
                    {
                        societyRecyclerViewInterface.onItemClick(position);
                    }
                }
            }
        });

    }

}

public class SocietyAdapter extends RecyclerView.Adapter<SocietyHolder>{
    private SocietyRecyclerViewInterface societyRecyclerViewInterface;
    private List<Society> s;
    private int count;

    public SocietyAdapter( List<Society> s,SocietyRecyclerViewInterface societyRecyclerViewInterface) {
        this.societyRecyclerViewInterface = societyRecyclerViewInterface;
        this.s = s;
    }

    @NonNull
    @Override
    public SocietyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_society, parent, false);
        return new SocietyHolder(view, societyRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull SocietyHolder holder, int position) {

        Society society = s.get(position);
        System.out.println("::::::::" + s.get(position).getSocietyId() + "::::::;");
        holder.society_tagline.setText(society.getSocietyTagline());
        System.out.println(society.getSocietyId());
        RetrofitService retrofitService = new RetrofitService();
        SocietyParticipationAPI societyParticipationAPI =  retrofitService.getRetrofit().create(SocietyParticipationAPI.class);

        societyParticipationAPI.getRatingBySID(society.getSocietyId()).enqueue(new Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, Response<Float> response) {

                String rate =String.valueOf(response.body());
                String value = "0.0";
                if(rate.length()>3)
                    value=rate.substring(0,4);
                else
                    value=rate;
                holder.society_rating.setText(String.valueOf(value));
                holder.society_name.setText(society.getSocietyName());
            }

            @Override
            public void onFailure(Call<Float> call, Throwable t) {

                holder.society_rating.setText(String.valueOf(0.0));
                holder.society_name.setText(society.getSocietyName());
            }
        });

        societyParticipationAPI.getMemberCountById(society.getSocietyId()).enqueue(new Callback<Integer>() {


            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                count = response.body();
                holder.noofmembers.setText(String.valueOf(count));

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                count = 0;
                holder.noofmembers.setText(String.valueOf(count));
            }
        });

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        DatabaseReference getImage = databaseReference.child("image");
        getImage.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String societyImageLink = society.getSocietyBackground();
                        if(societyImageLink!=null){
                            Picasso.get().load(societyImageLink).into(holder.imageView);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


    }

    @Override
    public int getItemCount() {
        return s.size();
    }
}

