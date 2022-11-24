package com.example.FrameUpServer.Model.SocietyParticipation;


import com.example.FrameUpServer.Model.Person.Person;
import jdk.jfr.DataAmount;

import javax.persistence.*;

@DataAmount

@Entity
public class SocietyParticipation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int participationId;
    private float rating;
    private String rollNo;
    private int societyId;
    private int participatedAs;

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

    public int getParticipatedAs() {
        return participatedAs;
    }

    public void setParticipatedAs(int participatedAs) {
        this.participatedAs = participatedAs;
    }

    @Override
    public String toString() {
        return "SocietyParticipation{" +
                "participationId=" + participationId +
                ", rating=" + rating +
                ", rollNo='" + rollNo + '\'' +
                ", societyId=" + societyId +
                ", participatedAs=" + participatedAs +
                '}';
    }
}