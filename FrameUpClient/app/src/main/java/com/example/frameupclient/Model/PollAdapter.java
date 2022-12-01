package com.example.frameupclient.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frameupclient.Activities.PollRecyclerViewInterface;
import com.example.frameupclient.R;

import java.util.List;

class PollHolder extends RecyclerView.ViewHolder {

    TextView poll_caption, poll_responses,popular_option, poll_society_related,poll_id_p;
    public PollHolder(@NonNull View itemView, PollRecyclerViewInterface pollRecyclerViewInterface) {
        super(itemView);
        poll_caption =itemView.findViewById(R.id.poll_statement_in_view);
        poll_responses=itemView.findViewById(R.id.poll_responses_in_row_poll);
        popular_option=itemView.findViewById(R.id.popular_option);
        poll_society_related=itemView.findViewById(R.id.poll_society_related);
        poll_id_p=itemView.findViewById(R.id.poll_id_row_poll);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pollRecyclerViewInterface!=null)
                {
                    int position = getBindingAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION)
                    {
                        pollRecyclerViewInterface.onItemClick(position);
                    }
                }
            }
        });

    }

}


public class PollAdapter extends RecyclerView.Adapter<PollHolder> {

    private PollRecyclerViewInterface pollRecyclerViewInterface;
    private List<Poll> p;

    public PollAdapter(List<Poll> p, PollRecyclerViewInterface pollRecyclerViewInterface) {
        this.p = p;
        this.pollRecyclerViewInterface =pollRecyclerViewInterface;
    }

    @NonNull
    @Override
    public PollHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_poll, parent, false);
        return new PollHolder(view, pollRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PollHolder holder, int position) {
        Poll poll = p.get(position);
        holder.poll_society_related.setText(poll.getSocietyRelated());
        holder.popular_option.setText("Options  : " + poll.getPollOption1() + "....");
        holder.poll_responses.setText(String.valueOf("Responses : " + poll.getNoOfResponses()));
        holder.poll_caption.setText(poll.getPollStatement());
        holder.poll_id_p.setText("Poll Id : "+poll.getPollId());
    }

    @Override
    public int getItemCount() {
        return p.size();
    }
}