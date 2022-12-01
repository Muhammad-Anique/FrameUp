package com.example.FrameUpServer.Model.PollResponded;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PollResponded {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int responseId;
    int pollId;
    String rollNo;

    public int getResponseId() {
        return responseId;
    }

    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    @Override
    public String toString() {
        return "PollResponded{" +
                "responseId=" + responseId +
                ", pollId=" + pollId +
                ", rollNo='" + rollNo + '\'' +
                '}';
    }
}
