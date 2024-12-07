package ru.stenyaeva.back.service;

import ru.stenyaeva.back.model.note.Note;
import ru.stenyaeva.back.model.user.User;

import java.util.List;

public interface NoteService {
    List<Note> getAllByOwner(User owner);
    Note save(Note note);
    Note update(Note note);
    void delete(Long id);
}
