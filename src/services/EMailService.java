package services;


import data.models.EMail;
import dtos.requests.SendEMailRequest;

import java.util.List;

public interface EMailService {
    String sendEmail(SendEMailRequest sendEMailRequest);
    void moveMailToTrash(int eMailId, String eMailType);
    List<EMail> viewAllMails();
    List<EMail> viewOutbox();
    List<EMail> viewDrafts();
    List<EMail> viewTrash();
}