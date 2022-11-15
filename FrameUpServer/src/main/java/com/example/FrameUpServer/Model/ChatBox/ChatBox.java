package com.example.FrameUpServer.Model.ChatBox;

import com.example.FrameUpServer.Model.Message.Message;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatBoxId;

    public int msgId;

    public int getChatBoxId() {
        return chatBoxId;
    }

    public void setChatBoxId(int chatBoxId) {
        this.chatBoxId = chatBoxId;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public void setChatBoxData(int chatBoxId, int msgId) {
        this.chatBoxId = chatBoxId;
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "ChatBox{" +
                "chatBoxId=" + chatBoxId +
                ", msgId=" + msgId +
                '}';
    }
}
