package com.example.FrameUpServer.Model.event;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId;
    private String eventSubject;
    private String dateEventCreated;
    private String eventType;
    private String societyRelated;
    private String authorName;
    private String eventMedia;
    private String eventText;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventSubject() {
        return eventSubject;
    }

    public void setEventSubject(String eventSubject) {
        this.eventSubject = eventSubject;
    }

    public String getDateEventCreated() {
        return dateEventCreated;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setDateEventCreated(String dateEventCreated) {
        this.dateEventCreated = dateEventCreated;
    }

    public String getSocietyRelated() {
        return societyRelated;
    }

    public void setSocietyRelated(String societyRelated) {
        this.societyRelated = societyRelated;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getEventMedia() {
        return eventMedia;
    }

    public void setEventMedia(String eventMedia) {
        this.eventMedia = eventMedia;
    }

    public String getEventText() {
        return eventText;
    }

    public void setEventText(String eventText) {
        this.eventText = eventText;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventSubject='" + eventSubject + '\'' +
                ", dateEventCreated='" + dateEventCreated + '\'' +
                ", eventType='" + eventType + '\'' +
                ", societyRelated='" + societyRelated + '\'' +
                ", authorName='" + authorName + '\'' +
                ", eventMedia='" + eventMedia + '\'' +
                ", eventText='" + eventText + '\'' +
                '}';
    }

}
