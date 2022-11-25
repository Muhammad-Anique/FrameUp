package com.example.frameupclient.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frameupclient.R;

import java.util.List;

class NotificationHolder extends RecyclerView.ViewHolder{

    TextView Heading,Text;
    public NotificationHolder(@NonNull View itemView) {
        super(itemView);
        Heading=itemView.findViewById(R.id.warningHeading);
        Text=itemView.findViewById(R.id.waringText);
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
        holder.Heading.setText(req.getRequestSubject());
        holder.Text.setText(req.getRequestText());
    }

    @Override
    public int getItemCount() {
        return r.size();
    }
}
