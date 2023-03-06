package data.repositories;

import data.models.EMail;

import java.util.List;

public interface EMailRepository {
    void addeMail(EMail eMail);
    void updateeMail(EMail eMail);
    void deleteeMail(EMail eMail);
    EMail findeMailById(int eMailId);
    List<EMail> findeMailsBySubject(String subject);
    List<EMail> findeMailsBySender(String sender);
    List<EMail> findAlleMails();
    List<EMail> getOutbox();
    List<EMail> getDrafts();
    List<EMail> getTrash();
}
