package com.example.FrameUpServer.Model.poll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int pollId;
    private String pollSubject;
    private String pollType;
    private String datePollCreated;
    private String createdBy;
    private int noOfResponses;
    private String pollText;
    private String pollOptions;

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public String getPollSubject() {
        return pollSubject;
    }

    public void setPollSubject(String pollSubject) {
        this.pollSubject = pollSubject;
    }

    public String getPollType() {
        return pollType;
    }

    public void setPollType(String pollType) {
        this.pollType = pollType;
    }

    public String getDatePollCreated() {
        return datePollCreated;
    }

    public void setDatePollCreated(String datePollCreated) {
        this.datePollCreated = datePollCreated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getNoOfResponses() {
        return noOfResponses;
    }

    public void setNoOfResponses(int noOfResponses) {
        this.noOfResponses = noOfResponses;
    }

    public String getPollText() {
        return pollText;
    }

    public void setPollText(String pollText) {
        this.pollText = pollText;
    }

    public String getPollOptions() {
        return pollOptions;
    }

    public void setPollOptions(String pollOptions) {
        this.pollOptions = pollOptions;
    }
}
