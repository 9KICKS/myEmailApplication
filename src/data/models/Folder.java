package data.models;

import java.util.List;

public class Folder {
    private int folderId;
    private String folderName;
    private List<EMail> eMails;

    public Folder(int folderId, String folderName, List<EMail> eMails) {
        this.folderId = folderId;
        this.folderName = folderName;
        this.eMails = eMails;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public List<EMail> geteMails() {
        return eMails;
    }

    public void seteMails(List<EMail> eMails) {
        this.eMails = eMails;
    }
}