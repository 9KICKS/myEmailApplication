package services;

import data.models.EMail;
import data.models.Folder;
import data.repositories.FolderRepository;
import data.repositories.FolderRepositoryImpl;
import utils.GenerateID;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FolderServiceImpl implements FolderService {
    private final FolderRepository folderRepository = new FolderRepositoryImpl();
    List<Folder> folders = folderRepository.findAllFolders();

    @Override
    public void createFolder(String folderName) {
        if (!folderExists(folderName)) {
            folders.add(new Folder(GenerateID.generateId(folders.size()),folderName, new ArrayList<>()));
            JOptionPane.showMessageDialog(null, "Folder " + folderName + " created successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Folder " + folderName + " already exists.");
        }
    }

    @Override
    public void renameFolder(String previousName, String newName) {
        if (folderExists(previousName)) {
            for (Folder folder : folders) {
                if (folder.getFolderName().equals(previousName)) {
                    folder.setFolderName(newName);
                    JOptionPane.showMessageDialog(null, "Folder " + previousName + " renamed to " + newName + " successfully.");
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Folder " + previousName + " does not exist.");
    }

    @Override
    public void moveMail(EMail eMail, String previousFolder, String newFolder) {
        if (folderExists(previousFolder)) {
            for (Folder previous : folders) {
                if (previous.getFolderName().equals(previousFolder)) {
                    if (previous.geteMails().contains(eMail)) {
                        if (folderExists(newFolder)) {
                            for (Folder destination : folders) {
                                if (destination.getFolderName().equals(newFolder)) {
                                    destination.geteMails().add(eMail);
                                    previous.geteMails().remove(eMail);
                                    JOptionPane.showMessageDialog(null, "Email moved from " + previousFolder + " to " + newFolder + " successfully.");
                                    return;
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Destination folder " + newFolder + " does not exist.");
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Email does not exist in the source folder " + previousFolder + ".");
                        return;
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Source folder " + previousFolder + " does not exist.");
    }

    @Override
    public void deleteFolder(String folderName) {
        if (folderExists(folderName)) {
            for (Folder folder : folders) {
                if (folder.getFolderName().equals(folderName)) {
                    folders.remove(folder);
                    JOptionPane.showMessageDialog(null, "Folder " + folderName + " deleted successfully.");
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Folder " + folderName + " does not exist.");
    }

    @Override
    public List<String> foldersList() {
        List<String> folderNames = new ArrayList<>();
        for (Folder folder : folders) {
            folderNames.add(folder.getFolderName());
        }
        return folderNames;
    }

    @Override
    public List<EMail> eMails(String folderName) {
        if (folderExists(folderName)) {
            for (Folder folder : folders) {
                if (folder.getFolderName().equals(folderName)) {
                    return folder.geteMails();
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Folder " + folderName + " does not exist.");
        return null;
    }

    private boolean folderExists(String folderName) {
        for (Folder folder : folders) {
            if (folder.getFolderName().equals(folderName)) {
                return true;
            }
        }
        return false;
    }
}