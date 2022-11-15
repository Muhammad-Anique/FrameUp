package com.example.FrameUpServer.Model.EmailServices;

import com.example.FrameUpServer.Model.Visitor.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@EnableAutoConfiguration
@Service
public class SendEmail {

    @Autowired
    private EmailSenderService service;

    public void sendTheEmail(String otp, String toEmail)
    {
        String text = "This is Your OTP = ";
        String subject ="FrameUp Verification OTP"   ;
        String body = text.concat(otp);
        System.out.println("otp = ");
        System.out.println(otp);
        System.out.println("email");
        System.out.println(toEmail);
        service.sendSimpleEmail(toEmail.toString(),body.toString(),subject.toString());
    }
}
