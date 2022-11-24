package com.example.FrameUpServer.Model.Requests;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int requestId;
    String requestType;
    String requestText;
    String sendBy;
    String sendTo;
    String societyId;
    String requestSubject;
    String requestColor;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestText() {
        return requestText;
    }

    public void setRequestText(String requestText) {
        this.requestText = requestText;
    }

    public String getSendBy() {
        return sendBy;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy;
    }

    public String getSocietyId() {
        return societyId;
    }

    public void setSocietyId(String societyId) {
        this.societyId = societyId;
    }

    public String getRequestSubject() {
        return requestSubject;
    }

    public void setRequestSubject(String requestSubject) {
        this.requestSubject = requestSubject;
    }

    public String getRequestColor() {
        return requestColor;
    }

    public void setRequestColor(String requestColor) {
        this.requestColor = requestColor;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", requestType='" + requestType + '\'' +
                ", requestText='" + requestText + '\'' +
                ", sendBy='" + sendBy + '\'' +
                ", sendTo='" + sendTo + '\'' +
                ", societyId='" + societyId + '\'' +
                ", requestSubject='" + requestSubject + '\'' +
                ", requestColor='" + requestColor + '\'' +
                '}';
    }
}
