package com.example.FrameUpServer;

import com.example.FrameUpServer.Model.EmailServices.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.event.EventListener;
@EnableAutoConfiguration
@SpringBootTest
public class EmailServiceTest {

	@Autowired
	private EmailSenderService service;

	@Test
    @EventListener(ApplicationReadyEvent.class)
	public void Trigger()
	{
		service.sendSimpleEmail("l202171@lhr.nu.edu.pk",
				"OTP is 3000",
				"Verification");
	}
}
