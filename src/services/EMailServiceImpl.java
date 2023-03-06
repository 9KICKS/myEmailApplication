package services;

import data.enums.EMailType;
import data.models.EMail;
import data.repositories.EMailRepository;
import data.repositories.EMailRepositoryImpl;
import dtos.requests.SendEMailRequest;
import utils.EmailSender;
import utils.GenerateID;
import utils.Mapper;
import java.time.LocalDateTime;
import java.util.List;


public class EMailServiceImpl implements EMailService {
    EMailRepository eMailRepository = new EMailRepositoryImpl();
    List<EMail> eMails = eMailRepository.findAlleMails();
    List<EMail> outbox = eMailRepository.getOutbox();
    List<EMail> drafts = eMailRepository.getDrafts();
    List<EMail> trash = eMailRepository.getTrash();

    @Override
    public String sendEmail(SendEMailRequest sendEMailRequest) {
        EMail eMail = Mapper.map(sendEMailRequest);
        eMail.seteMailId(GenerateID.generateId(eMails.size()));
        eMails.add(eMail);
        if (EmailSender.sendMail(sendEMailRequest)) {
            outbox.add(eMail);
        } else {
            drafts.add(eMail);
        }
        return null;
    }

    @Override
    public void moveMailToTrash(int eMailId, String eMailType) {
        if (eMailType.equalsIgnoreCase(String.valueOf(EMailType.DRAFT))) {
            for (EMail eMail : drafts) {
                if (eMail.geteMailId() == eMailId) {
                    eMail.setDeletedTime(LocalDateTime.now());
                    trash.add(eMail);
                    drafts.remove(eMail);
                    return;
                }
            }
        } else if (eMailType.equalsIgnoreCase(String.valueOf(EMailType.OUTBOX))) {
            for (EMail eMail : outbox) {
                if (eMail.geteMailId() == eMailId) {
                    eMail.setDeletedTime(LocalDateTime.now());
                    trash.add(eMail);
                    outbox.remove(eMail);
                    return;
                }
            }
        }
    }

    @Override
    public List<EMail> viewAllMails() {
        return eMails;
    }

    @Override
    public List<EMail> viewOutbox() {
        return outbox;
    }

    @Override
    public List<EMail> viewDrafts() {
        return drafts;
    }

    @Override
    public List<EMail> viewTrash() {
        return trash;
    }
}