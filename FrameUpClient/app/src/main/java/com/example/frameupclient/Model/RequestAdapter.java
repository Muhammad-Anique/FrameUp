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
import com.example.frameupclient.R;

import java.util.List;

class RequestHolder extends RecyclerView.ViewHolder{

    TextView Name,Mail;
    ImageView ImgRR;
    Button Add;
    public RequestHolder(@NonNull View itemView,RequestRvInterface requestRvInterface) {
        super(itemView);
        Name=itemView.findViewById(R.id.name_rr_lay);
        Mail=itemView.findViewById(R.id.email_rr_lay);
        ImgRR=itemView.findViewById(R.id.user_image_rr);
        Add=itemView.findViewById(R.id.Add_me_btn_rr);
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

        System.out.println("IN adapter" + r.get(position) + "About to bind");
        Request req = r.get(position);
        holder.Name.setText(req.getSendBy());
        holder.Mail.setText(req.getRequestSubject());
        if(req.getRequestType().compareTo("BecomeAdvisor")==0){
            holder.Add.setText("Add Advisor");}
        else if(req.getRequestType().compareTo("BecomeMember")==0){
            holder.Add.setText("Add Member");}
        else{
            holder.Add.setVisibility(View.INVISIBLE);
        }




    }

    @Override
    public int getItemCount() {
        return r.size();
    }
}
