package ru.stenyaeva.back.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import ru.stenyaeva.back.domain.exception.ResourceNotFoundException;
import ru.stenyaeva.back.model.note.Note;
import ru.stenyaeva.back.model.note.NoteImage;
import ru.stenyaeva.back.repository.NoteImageRepository;
import ru.stenyaeva.back.service.NoteImageService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteImageServiceImpl implements NoteImageService {
    private final NoteImageRepository noteImageRepository;

    @Override
    public NoteImage save(NoteImage noteImage) {
        return noteImageRepository.save(noteImage);
    }

    @Override
    public NoteImage getByNoteAndUUID(UUID uuid, Note note) {
        return noteImageRepository.findByIdAndNote(uuid, note).orElseThrow(() -> new ResourceNotFoundException("Картинка не найдена " + uuid));
    }


}
