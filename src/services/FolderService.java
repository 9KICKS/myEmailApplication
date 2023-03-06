package services;

import data.models.EMail;

import java.util.List;

public interface FolderService {
    void createFolder(String folderName);
    void renameFolder(String previousName, String newName);
    void moveMail(EMail eMail, String previousFolder, String newFolder);
    void deleteFolder(String folderName);
    List<String> foldersList();
    List<EMail> eMails(String folderName);
}