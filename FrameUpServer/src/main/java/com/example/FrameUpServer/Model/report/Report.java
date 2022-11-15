package com.example.FrameUpServer.Model.report;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Report {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reportAuthor;
    private String dateReportCreated;
    private float overallRating;
    private String reportSubject;
    private String reportBody;
    private String reportConclusion;
    private String societyName;
    private int noOfMembers;
    private int noOfFemales;
    private int noOfMales;
    private int noOfLikes;
    private int popularity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReportAuthor() {
        return reportAuthor;
    }

    public void setReportAuthor(String reportAuthor) {
        this.reportAuthor = reportAuthor;
    }

    public String getDateReportCreated() {
        return dateReportCreated;
    }

    public void setDateReportCreated(String dateReportCreated) {
        this.dateReportCreated = dateReportCreated;
    }

    public float getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(float overallRating) {
        this.overallRating = overallRating;
    }

    public String getReportSubject() {
        return reportSubject;
    }

    public void setReportSubject(String reportSubject) {
        this.reportSubject = reportSubject;
    }

    public String getReportBody() {
        return reportBody;
    }

    public void setReportBody(String reportBody) {
        this.reportBody = reportBody;
    }

    public String getReportConclusion() {
        return reportConclusion;
    }

    public void setReportConclusion(String reportConclusion) {
        this.reportConclusion = reportConclusion;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public int getNoOfMembers() {
        return noOfMembers;
    }

    public void setNoOfMembers(int noOfMembers) {
        this.noOfMembers = noOfMembers;
    }

    public int getNoOfFemales() {
        return noOfFemales;
    }

    public void setNoOfFemales(int noOfFemales) {
        this.noOfFemales = noOfFemales;
    }

    public int getNoOfMales() {
        return noOfMales;
    }

    public void setNoOfMales(int noOfMales) {
        this.noOfMales = noOfMales;
    }

    public int getNoOfLikes() {
        return noOfLikes;
    }

    public void setNoOfLikes(int noOfLikes) {
        this.noOfLikes = noOfLikes;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reportAuthor='" + reportAuthor + '\'' +
                ", dateReportCreated='" + dateReportCreated + '\'' +
                ", overallRating=" + overallRating +
                ", reportSubject='" + reportSubject + '\'' +
                ", reportBody='" + reportBody + '\'' +
                ", reportConclusion='" + reportConclusion + '\'' +
                ", societyName='" + societyName + '\'' +
                ", noOfMembers=" + noOfMembers +
                ", noOfFemales=" + noOfFemales +
                ", noOfMales=" + noOfMales +
                ", noOfLikes=" + noOfLikes +
                ", popularity=" + popularity +
                '}';
    }
}
