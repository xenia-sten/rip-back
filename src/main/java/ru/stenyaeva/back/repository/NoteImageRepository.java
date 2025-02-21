package ru.stenyaeva.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stenyaeva.back.model.note.Note;
import ru.stenyaeva.back.model.note.NoteImage;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NoteImageRepository extends JpaRepository<NoteImage, UUID> {
    Optional<NoteImage> findByIdAndNote(UUID id, Note note);
}
