package root.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final String NOREPLY_ADDRESS = "user987456321789@gmail.com";

    @Autowired
    private JavaMailSender emailSender;

    
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(NOREPLY_ADDRESS);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }
    
    public boolean validEmailAddress(String userEmail) {
    	
    	String splitStringEmail [] = userEmail.split("\\.");
    	
    	if (!(userEmail.contains("@"))) return false;
    	if (!(userEmail.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"))) return false;
    	if (splitStringEmail.length > 2) return false;
    	
    	String domain = splitStringEmail[splitStringEmail.length - 1].toLowerCase();
    	if(!(domain.equals("com") || domain.equals("edu") || domain.equals("gov") || domain.equals("net")))
    		return false;
    	
    	return true;
    	
    }

	   
}
