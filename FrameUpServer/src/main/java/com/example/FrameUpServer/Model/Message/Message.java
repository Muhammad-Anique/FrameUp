package com.example.FrameUpServer.Model.Message;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int msgId;

    private String body;

    private String senderId;

    private String receiverId;

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public void setMessageData(int msgId, String body, String senderId, String receiverId) {
        this.msgId = msgId;
        this.body = body;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgId='" + msgId + '\'' +
                ", body='" + body + '\'' +
                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                '}';
    }
}
