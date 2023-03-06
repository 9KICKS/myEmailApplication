package controllers;

import  java.util.List;
import data.models.EMail;
import dtos.requests.SendEMailRequest;
import services.EMailService;
import services.EMailServiceImpl;

public class EMailController {
private final EMailService eMailService = new EMailServiceImpl();

    public String sendeMail(SendEMailRequest request) {
        try {
            return eMailService.sendEmail(request);
        }
        catch (IllegalArgumentException error) {
            return error.getMessage();
        }
    }

    public List<EMail> getTrash() {
        return eMailService.viewTrash();
    }

    public List<EMail> findAlleMails() {
        return eMailService.viewAllMails();
    }

    public List<EMail> getOutbox() {
        return eMailService.viewOutbox();
    }

    public List<EMail> getDrafts() {
        return eMailService.viewDrafts();
    }
}