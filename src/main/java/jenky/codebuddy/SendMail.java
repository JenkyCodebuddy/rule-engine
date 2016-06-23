package jenky.codebuddy;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by joost on 3-6-2016.
 */
public class SendMail {

    /**
     * @param recipientEmail
     * @param verificationCode
     */
    public void sendVerifcationMail(String recipientEmail, String verificationCode){
	System.out.println("***************************Sending Verification Mail"****************************);
        final String username = "jenkycodebuddy@gmail.com";
        final String password = "buddycodejenky";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from-email@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail));
            message.setSubject("Verify code");
            message.setText("Your verification code is: " + verificationCode);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}


