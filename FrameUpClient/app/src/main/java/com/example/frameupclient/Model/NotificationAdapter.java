package com.example.frameupclient.Model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frameupclient.R;

import java.util.List;

class NotificationHolder extends RecyclerView.ViewHolder{

    TextView Heading,Text;
    ConstraintLayout noti;
    public NotificationHolder(@NonNull View itemView) {
        super(itemView);
        Heading=itemView.findViewById(R.id.warningHeading);
        Text=itemView.findViewById(R.id.waringText);
        noti=itemView.findViewById(R.id.noti_boti);
    }
}
public class NotificationAdapter extends RecyclerView.Adapter<NotificationHolder> {

    List<Request> r;

    public NotificationAdapter(List<Request> r) {
        this.r = r;
    }

    @NonNull
    @Override


    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notification, parent, false);
        return new NotificationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int position) {

        Request req = r.get(position);
        System.out.println(req);
        holder.Heading.setText(req.getRequestSubject());
        holder.Text.setText(req.getRequestText());

        if(req.getRequestType().compareTo("suspension")==0){
            holder.noti.setBackgroundColor((Color.parseColor("#A90303")));
        }
        else if(req.getRequestType().compareTo("warning")==0) {
            holder.noti.setBackgroundColor((Color.parseColor("#E6CB1C")));
        }
        else if(req.getRequestType().compareTo("youBecameMember")==0) {
            holder.noti.setBackgroundColor((Color.parseColor("#1bac10")));
        }
        else{
            holder.noti.setBackgroundColor((Color.parseColor("#E6CB1C")));
        }
//        else if(req.getRequestType().compareTo("SocietyCreated")==0){
//            holder.Heading.setText(req.getRequestSubject());
//            holder.Text.setText(req.getRequestText());
//        }
//        else if(req.getRequestType().compareTo("youBecameAdvisor")==0){
//            holder.Heading.setText(req.getRequestSubject());
//            holder.Text.setText(req.getRequestText());
//            holder.noti.setBackgroundColor((Color.parseColor("#E6CB1C")));
//        }


    }

    @Override
    public int getItemCount() {
        return r.size();
    }
}
