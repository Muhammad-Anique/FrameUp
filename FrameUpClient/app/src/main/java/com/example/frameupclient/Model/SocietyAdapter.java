package com.example.frameupclient.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frameupclient.Activities.SocietyRecyclerViewInterface;
import com.example.frameupclient.R;
import com.example.frameupclient.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class SocietyHolder extends RecyclerView.ViewHolder {

    TextView noofmembers, society_rating, society_tagline, society_name, society_badge;

    public SocietyHolder(@NonNull View itemView, SocietyRecyclerViewInterface societyRecyclerViewInterface) {
        super(itemView);

        noofmembers =itemView.findViewById(R.id.noof_member_row_society);
        society_rating=itemView.findViewById(R.id.rating_row_society);
        society_tagline=itemView.findViewById(R.id.row_society_tagline);
        society_name = itemView.findViewById(R.id.society_statement_in_view);
        society_badge = itemView.findViewById(R.id.society_badge);

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
        holder.society_tagline.setText(society.getSocietyTagline());
        System.out.println(society.getSocietyId());
        RetrofitService retrofitService = new RetrofitService();
        SocietyParticipationAPI societyParticipationAPI =  retrofitService.getRetrofit().create(SocietyParticipationAPI.class);
        societyParticipationAPI.getMemberCountById(society.getSocietyId()).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                count = response.body();
                System.out.println("((((((((");
                System.out.println(response.body());
                System.out.println(")))))))))))");

                System.out.println(count);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                count = 0;
            }
        });

        holder.noofmembers.setText(String.valueOf(count));
        holder.society_rating.setText(String.valueOf(society.getSocietyRating()));
        holder.society_name.setText(society.getSocietyName());

    }

    @Override
    public int getItemCount() {
        return s.size();
    }
}

