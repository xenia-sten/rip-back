package ru.stenyaeva.back.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Service;
import ru.stenyaeva.back.model.note.Note;
import ru.stenyaeva.back.model.user.User;
import ru.stenyaeva.back.repository.NoteRepository;
import ru.stenyaeva.back.service.NoteService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    @Override
    public List<Note> getAllByOwner(User owner) {
        return noteRepository.findAllByOwner(owner);
    }
    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note update(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

}
