package com.example.frameupclient.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frameupclient.Activities.RequestRvInterface;
import com.example.frameupclient.Activities.VisitorList;
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

class RequestHolder extends RecyclerView.ViewHolder{

    TextView Name,Mail,Roll,reqType;
    ImageView ImgRR;
    Button Add;
    public RequestHolder(@NonNull View itemView,RequestRvInterface requestRvInterface) {
        super(itemView);
        Name=itemView.findViewById(R.id.name_rr_lay);
        Roll =itemView.findViewById(R.id.roll_rr_lay);
        Mail=itemView.findViewById(R.id.email_rr_lay);
        ImgRR=itemView.findViewById(R.id.user_image_rr);
        Add=itemView.findViewById(R.id.Add_me_btn_rr);
        reqType=itemView.findViewById(R.id.req_type_rr_lay_);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(requestRvInterface!=null)
                {
                    int position = getBindingAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION)
                    {
                        requestRvInterface.onButtonClick(position);
                    }
                }
            }
        });
    }
}

public class RequestAdapter extends RecyclerView.Adapter<RequestHolder> {

    private List<Request> r;
    private RequestRvInterface requestRvInterface;


    public RequestAdapter(List<Request> r, RequestRvInterface requestRvInterface) {
        this.r = r;
        this.requestRvInterface = requestRvInterface;
    }

    @NonNull
    @Override
    public RequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_request, parent, false);
        return new RequestHolder(view, requestRvInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestHolder holder, int position) {

        System.out.println("In adapter" + r.get(position) + "About to bind");
        Request req = r.get(position);
        holder.reqType.setText(req.getRequestType());
        if(req.getRequestType().compareTo("becomeAdvisor")==0){
            holder.Add.setText("Add Advisor");}
        else if(req.getRequestType().compareTo("becomeMember")==0){
            holder.Add.setText("Add Member");}
        else if(req.getRequestType().compareTo("societyCreation")==0)
        {
            holder.Add.setText("Create");
        }
        else{
            holder.Add.setVisibility(View.INVISIBLE);
        }

        RetrofitService retrofitService = new RetrofitService();
        VisitorAPI visitorAPI = retrofitService.getRetrofit().create(VisitorAPI.class);
        visitorAPI.getVisitorByRollNo(req.getSendBy()).enqueue(new Callback<Visitor>() {
            @Override
            public void onResponse(Call<Visitor> call, Response<Visitor> response) {
                if(response.body()!=null) {
                    holder.Roll.setText(response.body().getEmail());
                    holder.Name.setText(response.body().getName());
                    holder.Mail.setText(req.getRequestText());
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                DatabaseReference getImage = databaseReference.child("image");
                getImage.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(response.body().getProfileUrl()!=null) {
                            String link = response.body().getProfileUrl();
                            String myString = link.substring(1, link.length() - 1);
                            myString = myString.replace("\\u003d", "=");
                            myString = myString.replace("\\u0026", "&");
                            System.out.println(myString + "hehehehehehe");
                            Picasso.get().load(myString).into(holder.ImgRR);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                }
            }

            @Override
            public void onFailure(Call<Visitor> call, Throwable t) {
                holder.Name.setText(req.getSendBy());
                holder.Roll.setVisibility(View.INVISIBLE);
                holder.Mail.setText(req.getRequestText());
            }
        });








    }

    @Override
    public int getItemCount() {
        return r.size();
    }
}
