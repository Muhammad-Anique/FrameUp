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
    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }


    public int getParticipationId() {
        return participationId;
    }

    public void setParticipationId(int participationId) {
        this.participationId = participationId;
    }

    public float getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "SocietyParticipation{" +
                "participationId=" + participationId +
                ", rating=" + rating +
                ", rollNo='" + rollNo + '\'' +
                '}';
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
