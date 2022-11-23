package com.example.frameupclient.Model;

public class SocietyParticipation {

    private int participationId;
    private float rating;
    private String rollNo;
    private int societyId;

    public int getParticipationId() {
        return participationId;
    }

    public void setParticipationId(int participationId) {
        this.participationId = participationId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public int getSocietyId() {
        return societyId;
    }

    public void setSocietyId(int societyId) {
        this.societyId = societyId;
    }

    @Override
    public String toString() {
        return "SocietyParticipation{" +
                "participationId=" + participationId +
                ", rating=" + rating +
                ", rollNo='" + rollNo + '\'' +
                ", societyId='" + societyId + '\'' +
                '}';
    }
}