package data.models;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EMail {
    private int eMailId;
    private String message;
    private String sender;
    private List<String> recipient;
    private String subject;
    private List<String> attachments;
    private LocalDateTime createdTime = LocalDateTime.now();
    private LocalDateTime deletedTime;

    public EMail() {}

    public EMail(int eMailId, String sender, List<String> recipient, String subject,  String message) {
        this.eMailId = eMailId;
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
        attachments = new ArrayList<>();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getRecipient() {
        return recipient;
    }

    public void setRecipient(List<String> recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int geteMailId() {
        return eMailId;
    }

    public void seteMailId(int eMailId) {
        this.eMailId = eMailId;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void sendMail() {
        JOptionPane.showMessageDialog(null, "eMail sent from " + sender + " to " + recipient);
    }

    public void receiveMail() {
        JOptionPane.showMessageDialog(null, "eMail received by " + recipient + " from " + sender);
    }

    public void deleteMail() {
        JOptionPane.showMessageDialog(null, "eMail deleted successfully.");
    }

    public void addAttachment(String attachment) {
        attachments.add(attachment);
    }

    public void removeAttachment(String attachment) {
        attachments.remove(attachment);
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public LocalDateTime getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(LocalDateTime deletedTime) {
        this.deletedTime = deletedTime;
    }
}