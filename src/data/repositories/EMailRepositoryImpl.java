package data.repositories;

import data.models.EMail;

import java.util.ArrayList;
import java.util.List;

public class EMailRepositoryImpl implements EMailRepository {
    private List<EMail> eMails;
    List<EMail> outbox = new ArrayList<>();
    List<EMail> drafts = new ArrayList<>();
    List<EMail> trash = new ArrayList<>();

    public EMailRepositoryImpl() {
        this.eMails = new ArrayList<>();
    }

    @Override
    public void addeMail(EMail eMail) {
        this.eMails.add(eMail);
    }

    @Override
    public void updateeMail(EMail eMail) {
        int index = eMails.indexOf(eMail);
        if (index >= 0) {
            this.eMails.set(index, eMail);
        }
    }

    @Override
    public void deleteeMail(EMail eMail) {
        this.eMails.remove(eMail);
    }

    @Override
    public EMail findeMailById(int eMailId) {
        for (EMail eMail : this.eMails) {
            if (eMail.geteMailId() == eMailId) {
                return eMail;
            }
        }
        return null;
    }

    @Override
    public List<EMail> findeMailsBySubject(String subject) {
        List<EMail> result = new ArrayList<>();
        for (EMail eMail : this.eMails) {
            if (eMail.getSubject().equals(subject)) {
                result.add(eMail);
            }
        }
        return null;
    }

    @Override
    public List<EMail> findeMailsBySender(String sender) {
        List<EMail> result = new ArrayList<>();
        for (EMail eMail : this.eMails) {
            if (eMail.getSender().equals(sender)) {
                result.add(eMail);
            }
        }
        return result;
    }

    @Override
    public List<EMail> findAlleMails() {
        return new ArrayList<>(this.eMails);
    }

    @Override
    public List<EMail> getOutbox() {
        return outbox;
    }

    @Override
    public List<EMail> getDrafts() {
        return drafts;
    }

    @Override
    public List<EMail> getTrash() {
        return trash;
    }
}