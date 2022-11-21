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
    private String datePollCreated;
    private String createdBy;
    private int noOfResponses;
    private String pollStatement;
    private String pollOptionNumber;

    private String pollOption1;
    private String optionResponses1;

    private String pollOption2;
    private String optionResponses2;

    private String pollOption3;
    private String optionResponses3;

    private String pollOption4;
    private String optionResponses4;



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

    public String getPollStatement() {
        return pollStatement;
    }

    public void setPollStatement(String pollStatement) {
        this.pollStatement = pollStatement;
    }

    public String getPollOptionNumber() {
        return pollOptionNumber;
    }

    public void setPollOptionNumber(String pollOptionNumber) {
        this.pollOptionNumber = pollOptionNumber;
    }

    public String getPollOption1() {
        return pollOption1;
    }

    public void setPollOption1(String pollOption1) {
        this.pollOption1 = pollOption1;
    }

    public String getOptionResponses1() {
        return optionResponses1;
    }

    public void setOptionResponses1(String optionResponses1) {
        this.optionResponses1 = optionResponses1;
    }

    public String getPollOption2() {
        return pollOption2;
    }

    public void setPollOption2(String pollOption2) {
        this.pollOption2 = pollOption2;
    }

    public String getOptionResponses2() {
        return optionResponses2;
    }

    public void setOptionResponses2(String optionResponses2) {
        this.optionResponses2 = optionResponses2;
    }

    public String getPollOption3() {
        return pollOption3;
    }

    public void setPollOption3(String pollOption3) {
        this.pollOption3 = pollOption3;
    }

    public String getOptionResponses3() {
        return optionResponses3;
    }

    public void setOptionResponses3(String optionResponses3) {
        this.optionResponses3 = optionResponses3;
    }

    public String getPollOption4() {
        return pollOption4;
    }

    public void setPollOption4(String pollOption4) {
        this.pollOption4 = pollOption4;
    }

    public String getOptionResponses4() {
        return optionResponses4;
    }

    public void setOptionResponses4(String optionResponses4) {
        this.optionResponses4 = optionResponses4;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "pollId=" + pollId +
                ", pollSubject='" + pollSubject + '\'' +
                ", datePollCreated='" + datePollCreated + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", noOfResponses=" + noOfResponses +
                ", pollStatement='" + pollStatement + '\'' +
                ", pollOptionNumber='" + pollOptionNumber + '\'' +
                ", pollOption1='" + pollOption1 + '\'' +
                ", optionResponses1='" + optionResponses1 + '\'' +
                ", pollOption2='" + pollOption2 + '\'' +
                ", optionResponses2='" + optionResponses2 + '\'' +
                ", pollOption3='" + pollOption3 + '\'' +
                ", optionResponses3='" + optionResponses3 + '\'' +
                ", pollOption4='" + pollOption4 + '\'' +
                ", optionResponses4='" + optionResponses4 + '\'' +
                '}';
    }
}
