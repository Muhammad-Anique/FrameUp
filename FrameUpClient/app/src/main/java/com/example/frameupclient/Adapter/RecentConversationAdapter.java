package com.example.frameupclient.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frameupclient.Model.ChatMessage;
import com.example.frameupclient.Model.Users;
import com.example.frameupclient.databinding.ItemContainerRecentConversionBinding;
import com.example.frameupclient.listeners.ConversionListener;

import java.util.List;

public class RecentConversationAdapter extends RecyclerView.Adapter<RecentConversationAdapter.ConversationViewHolder> {

    private final List<ChatMessage> chatMessageList;
    private final ConversionListener conversionListener;

    public RecentConversationAdapter(List<ChatMessage> chatMessageList, ConversionListener conversionListener) {
        this.chatMessageList = chatMessageList;
        this.conversionListener = conversionListener;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversationViewHolder(
                ItemContainerRecentConversionBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
        holder.setData(chatMessageList.get(position));
    }

    @Override
    public int getItemCount() {
        return chatMessageList.size();
    }

    class ConversationViewHolder extends RecyclerView.ViewHolder {

        ItemContainerRecentConversionBinding binding;

        ConversationViewHolder(ItemContainerRecentConversionBinding itemContainerRecentConversionBinding){
            super(itemContainerRecentConversionBinding.getRoot());
            binding = itemContainerRecentConversionBinding;
        }

        void setData(ChatMessage chatMessage){
            binding.imageProfile.setImageBitmap(getConversionImage(chatMessage.conversionImage));
            binding.textName.setText(chatMessage.conversionName);
            binding.textRecentMessage.setText(chatMessage.message);
            binding.getRoot().setOnClickListener(v-> {
                Users users = new Users();
                users.id = chatMessage.conversionId;
                users.name = chatMessage.conversionName;
                users.image = chatMessage.conversionImage;
                conversionListener.onConversionClicked(users);
            });
        }
    }

    private Bitmap getConversionImage(String encodedImage){
        byte[]  bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

}
