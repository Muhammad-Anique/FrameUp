package com.example.frameupclient.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frameupclient.Model.Users;
import com.example.frameupclient.databinding.ItemLayoutUserBinding;
import com.example.frameupclient.listeners.UserListener;

import java.util.Base64;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    private final List<Users> users;
    private final UserListener userListener;

    public UsersAdapter(List<Users> users, UserListener userListener){
        this.users = users;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutUserBinding itemLayoutUserBinding = ItemLayoutUserBinding.inflate(
          LayoutInflater.from(parent.getContext()),
          parent,
          false
        );
        return new UsersViewHolder(itemLayoutUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.setUserData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {

         ItemLayoutUserBinding binding;

        UsersViewHolder (ItemLayoutUserBinding itemLayoutUserBinding) {
              super(itemLayoutUserBinding.getRoot());
              binding = itemLayoutUserBinding;
        }

        void setUserData(Users user){
            binding.textName.setText(user.name);
            binding.textEmail.setText(user.email);
            binding.imageProfile.setImageBitmap(getUserImage(user.image));
            binding.getRoot().setOnClickListener(v -> userListener.onUserClicked(user));
        }

    }

    private Bitmap getUserImage (String encodedImage) {
        byte[] bytes = Base64.getDecoder().decode(encodedImage);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
