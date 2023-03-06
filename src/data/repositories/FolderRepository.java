package data.repositories;

import data.models.Folder;

import java.util.List;

public interface FolderRepository {
    void addFolder(Folder folder);
    void deleteFolder(Folder folder);
    List<Folder> findAllFolders();
}