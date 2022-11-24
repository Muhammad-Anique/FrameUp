package com.example.FrameUpServer.Model.Session;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionId;
    String RollNumber;

    public String getRollNumber() {
        return RollNumber;
    }

    public void setRollNumber(String rollNumber) {
        RollNumber = rollNumber;
    }

    public Session(int sessionId) {
        this.sessionId = sessionId;
    }
}
