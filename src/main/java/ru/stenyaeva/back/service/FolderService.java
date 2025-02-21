package ru.stenyaeva.back.service;

import ru.stenyaeva.back.model.note.Folder;
import ru.stenyaeva.back.model.user.User;

import java.util.List;

public interface FolderService {
    List<Folder> getAllByOwner(User owner);
    List<Folder> getAllByParent(Folder parent);
    List<Folder> getParentFolders(User owner);
    Folder getById(Long id);
    Folder save(Folder folder);
    Folder update(Folder folder);
    void delete(Long id);
}
