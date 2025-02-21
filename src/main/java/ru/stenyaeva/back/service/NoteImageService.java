package ru.stenyaeva.back.service;

import ru.stenyaeva.back.model.note.Note;
import ru.stenyaeva.back.model.note.NoteImage;

import java.util.UUID;

public interface NoteImageService {
    NoteImage save(NoteImage noteImage);
    NoteImage getByNoteAndUUID(UUID uuid, Note note);
}
