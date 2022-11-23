package com.example.frameupclient.Model;

public class Society {
    private int societyId;
    private String societyBackground;
    private String societyName;
    private String societyTagline;
    private String societyDescription;
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

    public String getSocietyBackground() {
        return societyBackground;
    }

    public void setSocietyBackground(String societyBackground) {
       this.societyBackground = societyBackground;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getSocietyTagline() {
        return societyTagline;
    }

    public void setSocietyTagline(String societyTagline) {
        this.societyTagline = societyTagline;
    }

    public String getSocietyDescription() {
        return societyDescription;
    }

    public void setSocietyDescription(String societyDescription) {
        this.societyDescription = societyDescription;
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
                ", SocietyBackground='" + societyBackground + '\'' +
                ", societyName='" + societyName + '\'' +
                ", societyTagline='" + societyTagline + '\'' +
                ", societyDescription='" + societyDescription + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", societyHead='" + societyHead + '\'' +
                ", societyRating=" + societyRating +
                ", societyLikes=" + societyLikes +
                '}';
    }
}
