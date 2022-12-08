package com.example.frameupclient.Model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frameupclient.R;

import java.util.List;

class NotificationHolder extends RecyclerView.ViewHolder{

    TextView Heading,Text;
    ImageView sign;
    ConstraintLayout noti;
    public NotificationHolder(@NonNull View itemView) {
        super(itemView);
        Heading=itemView.findViewById(R.id.warningHeading);
        Text=itemView.findViewById(R.id.waringText);
        noti=itemView.findViewById(R.id.noti_boti);
        sign=itemView.findViewById(R.id.warningSign);
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
            holder.sign.setImageResource(R.drawable.warning_sign);
        }
        else if(req.getRequestType().compareTo("becameMember")==0) {
            holder.noti.setBackgroundColor((Color.parseColor("#1bac10")));
            holder.sign.setImageResource(R.drawable.sign_popper);
        }
        else if(req.getRequestType().compareTo("becameAdvisor")==0) {
            holder.noti.setBackgroundColor((Color.parseColor("#3D90DC")));
            holder.sign.setImageResource(R.drawable.sign_popper);
        }
        else if(req.getRequestType().compareTo("meetingCall")==0) {
            holder.noti.setBackgroundColor((Color.parseColor("#993299")));
            holder.sign.setImageResource(R.drawable.sign_meeting);
        }
        else if(req.getRequestType().compareTo("interviewCall")==0) {
            holder.noti.setBackgroundColor((Color.parseColor("#ff8767")));
            holder.sign.setImageResource(R.drawable.sign_meeting);
        }
        else if(req.getRequestType().compareTo("reminder")==0) {
            holder.noti.setBackgroundColor((Color.parseColor("#ffb606")));
            holder.sign.setImageResource(R.drawable.sign_reminder);
        }
        else if(req.getRequestType().compareTo("welcome")==0) {
            holder.noti.setBackgroundColor((Color.parseColor("#ca4b78")));
            holder.sign.setImageResource(R.drawable.sign_meeting);
        }
        else if(req.getRequestType().compareTo("advertisement")==0) {
            holder.noti.setBackgroundColor((Color.parseColor("#ff2f9b")));
            holder.sign.setImageResource(R.drawable.sign_ad);
        }
        else if(req.getRequestType().compareTo("info")==0) {
            holder.noti.setBackgroundColor((Color.parseColor("#9453ff")));
            holder.sign.setImageResource(R.drawable.sign_info);
        }
        else if(req.getRequestType().compareTo("call")==0) {
            holder.noti.setBackgroundColor((Color.parseColor("#4f95ff")));
            holder.sign.setImageResource(R.drawable.sign_meeting);
        }
        else if(req.getRequestType().compareTo("notice")==0) {
            holder.noti.setBackgroundColor((Color.parseColor("#E6CB1C")));
            holder.sign.setImageResource(R.drawable.sign_info);
        }
        else{
            holder.noti.setBackgroundColor((Color.parseColor("#E6CB1C")));
        }

    }

    @Override
    public int getItemCount() {
        return r.size();
    }
}
