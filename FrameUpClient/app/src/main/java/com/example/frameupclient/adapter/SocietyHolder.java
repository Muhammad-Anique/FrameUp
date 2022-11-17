package com.example.frameupclient.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frameupclient.R;

public class SocietyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView name,head,category;
    OnClickListenerSociety onClickListenerSociety;
    public SocietyHolder(@NonNull View itemView, OnClickListenerSociety onClickListenerSociety) {
        super(itemView);
        name = itemView.findViewById(R.id.societylist_name);
        head = itemView.findViewById(R.id.societyHead_name);
        category = itemView.findViewById(R.id.societyCategory_name);
        this.onClickListenerSociety = onClickListenerSociety;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       onClickListenerSociety.OnSocietyClick(getAdapterPosition());
    }

    public interface OnClickListenerSociety{
        void OnSocietyClick(int position);
    }

}
