package com.example.FrameUpServer;

import com.example.FrameUpServer.Model.EmailServices.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(scanBasePackages = "com.example.FrameUpServer")
@EnableAutoConfiguration
public class FrameUpServerApplication {

	@Autowired
	private EmailSenderService service;

	public static void main(String[] args) {

		SpringApplication.run(FrameUpServerApplication.class, args);
	}


//	@EventListener(ApplicationReadyEvent.class)
//	public void Trigger()
//	{
//		service.sendSimpleEmail("l202171@lhr.nu.edu.pk",
//				"OTP is 29990",
//				"Verification");
//	}

}
