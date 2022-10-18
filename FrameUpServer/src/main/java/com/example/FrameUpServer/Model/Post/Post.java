package com.example.FrameUpServer.Model.Post;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private String postSubject;
    private String postCreationDate;
    private String postType;
    private String societyAssociated;
    private String authorName;
    private String postText;


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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }
    public void setPostAll(    String postSubject,String postCreationDate, String postType, String societyAssociated, String authorName, String postText
    )
    {
        setPostSubject(postSubject);
        setPostCreationDate(postCreationDate);
        setPostType(postType);
        setSocietyAssociated(societyAssociated);
        setAuthorName(authorName);
        setPostText(postText);
    }
    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postSubject='" + postSubject + '\'' +
                ", postCreationDate=" + postCreationDate +
                ", postType='" + postType + '\'' +
                ", societyAssociated='" + societyAssociated + '\'' +
                ", authorName='" + authorName + '\'' +
                ", postText='" + postText + '\'' +
                '}';
    }
}
