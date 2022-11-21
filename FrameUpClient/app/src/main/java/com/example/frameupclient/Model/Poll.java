package com.example.frameupclient.Model;

public class Poll {

    private int pollId;
    private String societyRelated;
    private String datePollCreated;
    private String createdBy;
    private int noOfResponses;
    private String pollStatement;
    private int totalOptions;
    private String pollOption1;
    private int option1Responses;
    private String pollOption2;
    private int option2Responses;
    private String pollOption3;
    private int option3Responses;
    private String pollOption4;
    private int option4Responses;
    private String pollOption5;
    private int option5Responses;

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public String getSocietyRelated() {
        return societyRelated;
    }

    public void setSocietyRelated(String societyRelated) {
        this.societyRelated = societyRelated;
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

    public int getTotalOptions() {
        return totalOptions;
    }

    public void setTotalOptions(int totalOptions) {
        this.totalOptions = totalOptions;
    }

    public String getPollOption1() {
        return pollOption1;
    }

    public void setPollOption1(String pollOption1) {
        this.pollOption1 = pollOption1;
    }

    public int getOption1Responses() {
        return option1Responses;
    }

    public void setOption1Responses(int option1Responses) {
        this.option1Responses = option1Responses;
    }

    public String getPollOption2() {
        return pollOption2;
    }

    public void setPollOption2(String pollOption2) {
        this.pollOption2 = pollOption2;
    }

    public int getOption2Responses() {
        return option2Responses;
    }

    public void setOption2Responses(int option2Responses) {
        this.option2Responses = option2Responses;
    }

    public String getPollOption3() {
        return pollOption3;
    }

    public void setPollOption3(String pollOption3) {
        this.pollOption3 = pollOption3;
    }

    public int getOption3Responses() {
        return option3Responses;
    }

    public void setOption3Responses(int option3Responses) {
        this.option3Responses = option3Responses;
    }

    public String getPollOption4() {
        return pollOption4;
    }

    public void setPollOption4(String pollOption4) {
        this.pollOption4 = pollOption4;
    }

    public int getOption4Responses() {
        return option4Responses;
    }

    public void setOption4Responses(int option4Responses) {
        this.option4Responses = option4Responses;
    }

    public String getPollOption5() {
        return pollOption5;
    }

    public void setPollOption5(String pollOption5) {
        this.pollOption5 = pollOption5;
    }

    public int getOption5Responses() {
        return option5Responses;
    }

    public void setOption5Responses(int option5Responses) {
        this.option5Responses = option5Responses;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "pollId=" + pollId +
                ", societyRelated='" + societyRelated + '\'' +
                ", datePollCreated='" + datePollCreated + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", noOfResponses=" + noOfResponses +
                ", pollStatement='" + pollStatement + '\'' +
                ", totalOptions=" + totalOptions +
                ", pollOption1='" + pollOption1 + '\'' +
                ", option1Responses=" + option1Responses +
                ", pollOption2='" + pollOption2 + '\'' +
                ", option2Responses=" + option2Responses +
                ", pollOption3='" + pollOption3 + '\'' +
                ", option3Responses=" + option3Responses +
                ", pollOption4='" + pollOption4 + '\'' +
                ", option4Responses=" + option4Responses +
                ", pollOption5='" + pollOption5 + '\'' +
                ", option5Responses=" + option5Responses +
                '}';
    }
}
