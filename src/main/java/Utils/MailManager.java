package Utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailManager {
    private Properties properties;
    private Authenticator authenticator;
    private final String fromEmail;

    public MailManager(String fromEmail, String fromPassword){
        this.fromEmail = fromEmail;
        setParameters(fromEmail, fromPassword);
    }

    public void sendEmail(String toEmail, String subject, String body){
        try {
            Session session = Session.getInstance(properties, authenticator);
            Message message = prepareMessage(session, toEmail);
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Message prepareMessage(Session session, String toEmail) throws MessagingException {
        MimeMessage message = new MimeMessage(session);

        // Set Email Headers
        message.addHeader("Content-type", "text/HTML; charset=UTF-8");
        message.addHeader("format", "flowed");
        message.addHeader("Content-Transfer-Encoding", "8bit");

        // Set From/Recipient Parameters
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

        return message;
    }

    private void setParameters(String fromEmail, String fromPassword){
        properties = System.getProperties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromPassword);
            }
        };
    }

    public static void main(String[] args) {
        String fromEmail = "menotify89@gmail.com";
        String fromPassword = "2Wsd8A8=[K";

        MailManager mailManager = new MailManager(fromEmail, fromPassword);
        mailManager.sendEmail("fkcr49@gmail.com","Hello World", "Hello Hello Hello");
    }
}
