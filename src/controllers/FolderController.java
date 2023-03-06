package controllers;

import data.models.EMail;
import services.FolderService;
import services.FolderServiceImpl;

import javax.swing.*;

public class FolderController {
    private final FolderService folderService = new FolderServiceImpl();

    public Object createFolder(String folderName) {
        try {
            folderService.createFolder(folderName);
        }
        catch (IllegalArgumentException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        return null;
    }

    public void renameFolder(String previousName, String newName) {
        try {
            folderService.renameFolder(previousName, newName);
        }
        catch (IllegalArgumentException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }

    public void moveMail(EMail eMail, String previousFolder, String newFolder) {
        try {
            folderService.moveMail(eMail, previousFolder, newFolder);
        }
        catch (IllegalArgumentException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }

    public void deleteFolder(String folderName) {
        try {
            folderService.deleteFolder(folderName);
        }
        catch (IllegalArgumentException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
}