package root.service.mail;

public interface EmailServiceInterface {
	void sendSimpleMessage(String to, String subject, String text);
}