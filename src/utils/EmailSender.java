package utils;

import dtos.requests.SendEMailRequest;
import resources.Prop;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;

public class EmailSender {
    public static boolean sendMail(SendEMailRequest sendEmailRequest) {
        String senderPassword = Prop.password;
        List<String> recipients = sendEmailRequest.getRecipient();
        if (recipients != null) {
            for (String email : recipients) {
                try {
                    InternetAddress.parse(email);
                } catch (AddressException e) {
                }
                String subject = sendEmailRequest.getSubject();
                String message = sendEmailRequest.getMessage();
                sendEMail(senderPassword, email, subject, message);
            }
        }
        return false;
    }

    public static void sendEMail(String senderPassword, String recipientEmail, String subject, String message) {
        String senderEmail = Prop.senderEmail;
        if (senderEmail == null || recipientEmail == null) {
            JOptionPane.showMessageDialog(null, "Error sending email. Invalid email addresses");
            return;
        }
        Properties properties = addPropertiesValue();
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        try {
            MimeMessage mimeMessage = buildMimeMessage(senderEmail,recipientEmail,subject,message,session);
            Transport.send(mimeMessage);
            JOptionPane.showMessageDialog(null, "Email sent successfully.");
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Error sending email: " + e.getMessage());
        }
    }

    private static MimeMessage buildMimeMessage(String senderEmail, String recipientEmail, String subject, String message, Session session) throws MessagingException {
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(senderEmail));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(message);
        return mimeMessage;
    }

    private static Properties addPropertiesValue() {
        Properties props = new Properties();
        props.put("mail.smtp.user","username");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.EnableSSL.enable","true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        return props;
    }
}