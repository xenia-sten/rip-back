package ru.stenyaeva.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stenyaeva.back.model.note.Note;
import ru.stenyaeva.back.model.user.User;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByOwner(User owner);

}
