package com.example.FrameUpServer.Model.Society;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Society {
    @Id
    @GeneratedValue
    private int societyId;
    private String societyName;
    private String societyMotive;
    private String societyCategory;
    private String dateCreated;
    private String societyHead;
    private float societyRating;
    private int societyLikes;

    public int getSocietyId() {
        return societyId;
    }

    public void setSocietyId(int societyId) {
        this.societyId = societyId;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getSocietyMotive() {
        return societyMotive;
    }

    public void setSocietyMotive(String societyMotive) {
        this.societyMotive = societyMotive;
    }

    public String getSocietyCategory() {
        return societyCategory;
    }

    public void setSocietyCategory(String societyCategory) {
        this.societyCategory = societyCategory;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getSocietyHead() {
        return societyHead;
    }

    public void setSocietyHead(String societyHead) {
        this.societyHead = societyHead;
    }

    public float getSocietyRating() {
        return societyRating;
    }

    public void setSocietyRating(float societyRating) {
        this.societyRating = societyRating;
    }

    public int getSocietyLikes() {
        return societyLikes;
    }

    public void setSocietyLikes(int societyLikes) {
        this.societyLikes = societyLikes;
    }

    @Override
    public String toString() {
        return "Society{" +
                "societyId=" + societyId +
                ", societyName='" + societyName + '\'' +
                ", societyMotive='" + societyMotive + '\'' +
                ", societyCategory='" + societyCategory + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", societyHead='" + societyHead + '\'' +
                ", societyRating=" + societyRating +
                ", societyLikes=" + societyLikes +
                '}';
    }
}
