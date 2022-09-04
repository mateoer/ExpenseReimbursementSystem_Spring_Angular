package root.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;

import root.service.mail.EmailService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmailServiceTest {
	
	@Mock
	private EmailService emailService;	
	
	private EmailService instanceOfEmailService = new EmailService();
	
	@Test
	void sendSimpleMessage() throws MailException {
		
		//It will accept any string for email bc it's verifying the method can send messages, not the validity of addresses
		String to = "eadadaeo5675@gsasamail.com";  
		String subject = "Reimbursement APPROVED";
		String text = "Hello, your reimbursement has been approved";		
	
		emailService.sendSimpleMessage(to, subject, text);
		
		verify(emailService, times(1)).sendSimpleMessage(to, subject, text);
		
	}
	
		
	@Test
	void validEmailAddress() {
		
		boolean otherDomanString = instanceOfEmailService.validEmailAddress("user987456321789@gmail.rus");		
		boolean comString = instanceOfEmailService.validEmailAddress("ericmateo5675@gmail.com");		
		boolean eduString = instanceOfEmailService.validEmailAddress("user987456321789@gmail.edu");
		boolean govString = instanceOfEmailService.validEmailAddress("user987456321789@gmail.gov");
		boolean netString = instanceOfEmailService.validEmailAddress("user987456321789@gmail.net");
		
		boolean randomString = instanceOfEmailService.validEmailAddress("asadafdffgfsg");
		boolean emptyString = instanceOfEmailService.validEmailAddress("");
		boolean onlyAtString = instanceOfEmailService.validEmailAddress("sdlkaalka@KLDSAKAS");
		boolean onlyDotString = instanceOfEmailService.validEmailAddress("sdlkaalka.KLDSAKAS");		
		
		assertAll(
				()-> assertFalse(randomString, "randomString"),
				()-> assertFalse(emptyString, "emptyString"),
				()-> assertFalse(onlyAtString, "onlyAtString"),
				()-> assertFalse(onlyDotString, "onlyDotString"),
				
				()-> assertTrue(otherDomanString, "otherDomanString"),
				()-> assertTrue(comString, "comString"),
				()-> assertTrue(eduString, "eduString"),
				()-> assertTrue(govString, "govString"),
				()-> assertTrue(netString, "netString")
				);
		
	}

}
