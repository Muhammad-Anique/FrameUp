package com.example.frameupclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frameupclient.Model.Society;
import com.example.frameupclient.R;

import java.util.List;

public class SocietyAdapter extends RecyclerView.Adapter<SocietyHolder> {

    private List<Society> societyList;
    private SocietyHolder.OnClickListenerSociety onClickListenerSociety;
    public SocietyAdapter(List<Society> societyList, SocietyHolder.OnClickListenerSociety onClickListenerSociety1) {
        this.societyList = societyList;
        this.onClickListenerSociety = onClickListenerSociety1;
    }

    @NonNull
    @Override
    public SocietyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_society,parent, false);
        return new SocietyHolder(view, onClickListenerSociety);
    }

    @Override
    public void onBindViewHolder(@NonNull SocietyHolder holder, int position) {
        Society society = societyList.get(position);
        holder.name.setText(society.getSocietyName());
        holder.head.setText(society.getSocietyHead());
        holder.category.setText(society.getSocietyCategory());
    }

    @Override
    public int getItemCount() {
        return societyList.size();
    }
}
