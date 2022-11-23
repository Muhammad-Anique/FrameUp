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
    String societyId;
    String requestSubject;
    String requestColor;

}
