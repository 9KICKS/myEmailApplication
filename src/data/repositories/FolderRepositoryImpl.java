package data.repositories;

import data.models.Folder;

import java.util.ArrayList;
import java.util.List;

public class FolderRepositoryImpl implements  FolderRepository {
    private final List<Folder> folders;

    public FolderRepositoryImpl() {
        this.folders = new ArrayList<>();
    }

    @Override
    public void addFolder(Folder folder) {
        folders.add(folder);
    }

    @Override
    public void deleteFolder(Folder folder) {
        folders.remove(folder);
    }

    @Override
    public List<Folder> findAllFolders() {
        return folders;
    }
}
