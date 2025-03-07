package ru.stenyaeva.back.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stenyaeva.back.model.note.Folder;
import ru.stenyaeva.back.model.note.Note;
import ru.stenyaeva.back.model.user.User;
import ru.stenyaeva.back.repository.FolderRepository;
import ru.stenyaeva.back.repository.NoteRepository;
import ru.stenyaeva.back.service.FolderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {
    private final FolderRepository folderRepository;
    private final NoteRepository noteRepository;

    public List<Folder> getAllByOwner (User owner){
        return folderRepository.findAllByOwner(owner);
    }

    @Override
    public List<Folder> getAllByParent(Folder parent) {
        return folderRepository.findAllByParent(parent);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Folder> getParentFolders(User owner) {
        return folderRepository.findAllByOwnerAndParentIsNull(owner);
    }

    @Override
    public Folder getById(Long id) {
        return folderRepository.findById(id).orElseThrow();
    }

    @Override
    public Folder save(Folder folder) {
        return folderRepository.save(folder);
    }

    @Override
    public Folder update(Folder folder) {
        return folderRepository.save(folder);
    }

    @Override
    public void delete(Long id) {
        List<Folder> children = folderRepository.findAllByParent(folderRepository.getById(id));
        for (Folder child : children) {
            folderRepository.delete(child);
        }
        folderRepository.deleteById(id);
    }
}
