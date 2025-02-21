package ru.stenyaeva.back.service;

import ru.stenyaeva.back.model.note.Folder;
import ru.stenyaeva.back.model.note.Note;
import ru.stenyaeva.back.model.user.User;

import java.util.List;

public interface NoteService {
    List<Note> getAllByFolder(Folder folder);
    Note getById(Long id);
    Note save(Note note);
    Note update(Note note);
    void delete(Long id);
}
