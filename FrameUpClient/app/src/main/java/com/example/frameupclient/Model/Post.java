package com.example.frameupclient.Model;
public class Post {

    private int postId;
    private String postSubject;
    private String postCreationDate;
    private String postType;
    private String societyAssociated;
    private String hashtag;
    private int priority;
    private String authorRoll;
    private String postText;
    private String link;
    private String eventDate;
    private String eventStartTime;
    private String eventVenue;
    private String eventEndTime;
    private String eventType;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostSubject() {
        return postSubject;
    }

    public void setPostSubject(String postSubject) {
        this.postSubject = postSubject;
    }

    public String getPostCreationDate() {
        return postCreationDate;
    }

    public void setPostCreationDate(String postCreationDate) {
        this.postCreationDate = postCreationDate;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getSocietyAssociated() {
        return societyAssociated;
    }

    public void setSocietyAssociated(String societyAssociated) {
        this.societyAssociated = societyAssociated;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getAuthorRoll() {
        return authorRoll;
    }

    public void setAuthorRoll(String authorRoll) {
        this.authorRoll = authorRoll;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postSubject='" + postSubject + '\'' +
                ", postCreationDate='" + postCreationDate + '\'' +
                ", postType='" + postType + '\'' +
                ", societyAssociated='" + societyAssociated + '\'' +
                ", hashtag='" + hashtag + '\'' +
                ", priority=" + priority +
                ", authorRoll='" + authorRoll + '\'' +
                ", postText='" + postText + '\'' +
                ", link='" + link + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", eventStartTime='" + eventStartTime + '\'' +
                ", eventVenue='" + eventVenue + '\'' +
                ", eventEndTime='" + eventEndTime + '\'' +
                ", eventType='" + eventType + '\'' +
                '}';
    }
}
