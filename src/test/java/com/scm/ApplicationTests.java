package com.scm;

import com.scm.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailService emailService;

	@Test
	void sendEmail() {
		emailService.sendEmail("kapilchaudhary024@gmail.com",
				"just testing email service",
				"this is scm project working on scm project");
	}

}
