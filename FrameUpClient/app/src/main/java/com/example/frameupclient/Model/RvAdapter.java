package com.example.frameupclient.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frameupclient.Activities.ViewPost;
import com.example.frameupclient.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

class RowHolder extends RecyclerView.ViewHolder {
    ImageView postImg, authorImg;
    TextView authorName, authorEmail, caption, hashtag, noOfLikes ;
    public RowHolder(@NonNull View itemView) {
        super(itemView);
        authorName = itemView.findViewById(R.id.post_author_name);
        authorEmail =itemView.findViewById(R.id.post_author_mail);
        caption =itemView.findViewById(R.id.post_caption_text);
        hashtag = itemView.findViewById(R.id.post_hashtags_tf);
        noOfLikes =itemView.findViewById(R.id.post_noof_likes);
        postImg = itemView.findViewById(R.id.author_image_post);
        authorImg =itemView.findViewById(R.id.post_image_media);
    }

}

class AdHolder extends RecyclerView.ViewHolder {
    ImageView img;
    public AdHolder(@NonNull View itemView) {
        super(itemView);
        img=itemView.findViewById(R.id.ivAd);
    }
}

public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Post> postList;

    public RvAdapter(List<Post> p) {
        this.postList = p;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType==1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_newsfeed, parent, false);
            return new RowHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_post, parent, false);
            return new AdHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return postList.get(position).getPriority();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {



        Post post = postList.get(position);
        if(post.getPriority()==1) {
            ((RowHolder) holder).authorName.setText(post.getAuthorRoll());
            ((RowHolder) holder).hashtag.setText(post.getHashtag());
            ((RowHolder) holder).caption.setText(post.getPostText());
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference();
            DatabaseReference getImage = databaseReference.child("image");
            getImage.addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String link = "https://firebasestorage.googleapis.com/v0/b/frameupclientapp.appspot.com/o/1668777835243.null?alt=media&token=568850fc-95a7-4494-8320-d52f75678c3e";
                            System.out.println(link);
                            Picasso.get().load(link).into(((RowHolder) holder).authorImg);
                            Picasso.get().load(link).into(((RowHolder) holder).postImg);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
        }else{

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference();
            DatabaseReference getImage = databaseReference.child("image");
            getImage.addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String link = post.getLink();
                            System.out.println(link);
                            Picasso.get().load(link).into(((AdHolder) holder).img);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
        }
    }
    @Override
    public int getItemCount() {
        return postList.size();
    }

}


