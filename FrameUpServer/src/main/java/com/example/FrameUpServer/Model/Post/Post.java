package com.example.FrameUpServer.Model.Post;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private String postSubject;
    private String postCreationDate;
    private String postType;
    private String societyAssociated;
    private String hashtag;
    private int priority;
    private String authorRoll;
    private String postText;
    private String link;


    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }



    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostSubject() {
        return postSubject;
    }

    public void setPostSubject(String postSubject) {
        this.postSubject = postSubject;
    }



    public String getPostType() {
        return postType;
    }

    public String getPostCreationDate() {
        return postCreationDate;
    }

    public void setPostCreationDate(String postCreationDate) {
        this.postCreationDate = postCreationDate;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getSocietyAssociated() {
        return societyAssociated;
    }

    public void setSocietyAssociated(String societyAssociated) {
        this.societyAssociated = societyAssociated;
    }

    public String getAuthorRoll() {
        return authorRoll;
    }

    public void setAuthorRoll(String authorName) {
        this.authorRoll = authorName;
    }

    public String getPostText() {
        return postText;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public void setPostText(String postText) {
        this.postText = postText;
    }
    public void setPostAll(String postSubject,String postCreationDate, String postType, String societyAssociated, String authorRoll, String postText, String link,String hashtag, int priority
    )
    {
        setPostSubject(postSubject);
        setPostCreationDate(postCreationDate);
        setPostType(postType);
        setSocietyAssociated(societyAssociated);
        setAuthorRoll(authorRoll);
        setPostText(postText);
        setLink(link);
        setPriority(priority);
        setHashtag(hashtag);

    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postSubject='" + postSubject + '\'' +
                ", postCreationDate='" + postCreationDate + '\'' +
                ", postType='" + postType + '\'' +
                ", societyAssociated='" + societyAssociated + '\'' +
                ", hashtag='" + hashtag + '\'' +
                ", priority=" + priority +
                ", authorRoll='" + authorRoll + '\'' +
                ", postText='" + postText + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
