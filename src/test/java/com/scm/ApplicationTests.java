package com.scm;

import com.scm.entities.Contact;
import com.scm.repository.ContactRepo;
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

//	@Test
//	void sendEmail() {
//		emailService.sendEmail("kapilchaudhary0240@gmail.com",
//				"just testing email service",
//				"this is scm project working on scm project");
//	}

	@Autowired
	private ContactRepo contactRepo;

	@Test
	void deleteContact(){
		this.contactRepo.deleteById("58ac4418-dc67-49c5-a7ec-fdc4f640c2c9");
	}

}
