package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

	public static boolean sendEmail(String to, String from, String access_token) {

		final String username = "comp9322renewal@gmail.com";// change
															// accordingly
		final String password = "comp9322";// change accordingly
		// email host
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// get session
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// init message object
			MimeMessage message = new MimeMessage(session);

			// Set From
			message.setFrom(new InternetAddress(from));

			// Set To
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject
			message.setSubject("Your driver license needs renewal");

			// 发送 HTML 消息, 可以插入html标签
			String url1 = "<a href=\"http://localhost:8080/AssignValidationClient/DriverLink1?token=" + access_token
					+ "\">Go to renewal page</a>";
			String url2 = "<a href=\"http://localhost:8080/AssignValidationClient/DriverLink2?token=" + access_token
					+ "\">Check renewal process</a>";
			message.setContent("<h1>Please start your renewal process by clicking the link: </h1>" + url1
					+ "<h1>Please check yours process by clicking the link below: </h1>" + url2, "text/html");

			// send
			Transport.send(message);
			System.out.println("Email sent");
			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
	}
}
