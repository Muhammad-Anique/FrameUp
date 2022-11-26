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
    private String reportType;
    private String dateReportCreated;
    private String reportSubject;
    private String reportBody;
    private String reportConclusion;
    private int societyId;
    private int noOfFemalesBatch18;
    private int noOfMalesBatch18;
    private int noOfFemalesBatch19;
    private int noOfMalesBatch19;
    private int noOfFemalesBatch20;
    private int noOfMalesBatch20;
    private int noOfFemalesBatch21;
    private int noOfMalesBatch21;
    private int noOfFemalesBatch22;
    private int noOfMalesBatch22;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getDateReportCreated() {
        return dateReportCreated;
    }

    public void setDateReportCreated(String dateReportCreated) {
        this.dateReportCreated = dateReportCreated;
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

    public int getSocietyId() {
        return societyId;
    }

    public void setSocietyId(int societyId) {
        this.societyId = societyId;
    }

    public int getNoOfFemalesBatch18() {
        return noOfFemalesBatch18;
    }

    public void setNoOfFemalesBatch18(int noOfFemalesBatch18) {
        this.noOfFemalesBatch18 = noOfFemalesBatch18;
    }

    public int getNoOfMalesBatch18() {
        return noOfMalesBatch18;
    }

    public void setNoOfMalesBatch18(int noOfMalesBatch18) {
        this.noOfMalesBatch18 = noOfMalesBatch18;
    }

    public int getNoOfFemalesBatch19() {
        return noOfFemalesBatch19;
    }

    public void setNoOfFemalesBatch19(int noOfFemalesBatch19) {
        this.noOfFemalesBatch19 = noOfFemalesBatch19;
    }

    public int getNoOfMalesBatch19() {
        return noOfMalesBatch19;
    }

    public void setNoOfMalesBatch19(int noOfMalesBatch19) {
        this.noOfMalesBatch19 = noOfMalesBatch19;
    }

    public int getNoOfFemalesBatch20() {
        return noOfFemalesBatch20;
    }

    public void setNoOfFemalesBatch20(int noOfFemalesBatch20) {
        this.noOfFemalesBatch20 = noOfFemalesBatch20;
    }

    public int getNoOfMalesBatch20() {
        return noOfMalesBatch20;
    }

    public void setNoOfMalesBatch20(int noOfMalesBatch20) {
        this.noOfMalesBatch20 = noOfMalesBatch20;
    }

    public int getNoOfFemalesBatch21() {
        return noOfFemalesBatch21;
    }

    public void setNoOfFemalesBatch21(int noOfFemalesBatch21) {
        this.noOfFemalesBatch21 = noOfFemalesBatch21;
    }

    public int getNoOfMalesBatch21() {
        return noOfMalesBatch21;
    }

    public void setNoOfMalesBatch21(int noOfMalesBatch21) {
        this.noOfMalesBatch21 = noOfMalesBatch21;
    }

    public int getNoOfFemalesBatch22() {
        return noOfFemalesBatch22;
    }

    public void setNoOfFemalesBatch22(int noOfFemalesBatch22) {
        this.noOfFemalesBatch22 = noOfFemalesBatch22;
    }

    public int getNoOfMalesBatch22() {
        return noOfMalesBatch22;
    }

    public void setNoOfMalesBatch22(int noOfMalesBatch22) {
        this.noOfMalesBatch22 = noOfMalesBatch22;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reportType='" + reportType + '\'' +
                ", dateReportCreated='" + dateReportCreated + '\'' +
                ", reportSubject='" + reportSubject + '\'' +
                ", reportBody='" + reportBody + '\'' +
                ", reportConclusion='" + reportConclusion + '\'' +
                ", societyId=" + societyId +
                ", noOfFemalesBatch18=" + noOfFemalesBatch18 +
                ", noOfMalesBatch18=" + noOfMalesBatch18 +
                ", noOfFemalesBatch19=" + noOfFemalesBatch19 +
                ", noOfMalesBatch19=" + noOfMalesBatch19 +
                ", noOfFemalesBatch20=" + noOfFemalesBatch20 +
                ", noOfMalesBatch20=" + noOfMalesBatch20 +
                ", noOfFemalesBatch21=" + noOfFemalesBatch21 +
                ", noOfMalesBatch21=" + noOfMalesBatch21 +
                ", noOfFemalesBatch22=" + noOfFemalesBatch22 +
                ", noOfMalesBatch22=" + noOfMalesBatch22 +
                '}';
    }
}
