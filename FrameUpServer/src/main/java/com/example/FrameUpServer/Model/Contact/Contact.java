package com.example.FrameUpServer.Model.Contact;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatBoxId;

    private String receiverId;

    private String senderId;

    public int getChatBoxId() {
        return chatBoxId;
    }

    public void setChatBoxId(int chatBoxId) {
        this.chatBoxId = chatBoxId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String rec_Id) {
        receiverId = rec_Id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String Id) {
        senderId = Id;
    }

    public void setContactData(int chatBoxId, String rec_Id, String sen_Id) {
        this.chatBoxId = chatBoxId;
        this.receiverId = rec_Id;
        this.senderId = sen_Id;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "chatBoxId=" + chatBoxId +
                ", ContactNo='" + receiverId + '\'' +
                ", Rollno='" + senderId + '\'' +
                '}';
    }
}
