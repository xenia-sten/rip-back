package ru.stenyaeva.back.controller;

import jakarta.servlet.annotation.HttpConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stenyaeva.back.domain.dto.note.CreateNoteDto;
import ru.stenyaeva.back.domain.dto.note.CreateNoteImageDto;
import ru.stenyaeva.back.domain.dto.note.NoteDto;
import ru.stenyaeva.back.domain.utils.SecurityUtils;
import ru.stenyaeva.back.model.note.Note;
import ru.stenyaeva.back.model.note.NoteImage;
import ru.stenyaeva.back.service.FolderService;
import ru.stenyaeva.back.service.NoteImageService;
import ru.stenyaeva.back.service.NoteService;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class NoteController {
    private final SecurityUtils securityUtils;
    private final NoteService noteService;
    private final FolderService folderService;
    private final NoteImageService noteImageService;


    @GetMapping("/folders/{id}/notes")
    public List<NoteDto> getAll(@PathVariable("id") Long id){
        return noteService.getAllByFolder(folderService.getById(id)).stream().map(NoteDto::new).toList();//{
    }

    @PostMapping("/notes/{id}/image")
    public String saveImage(@PathVariable("id") Long id,
            @RequestBody CreateNoteImageDto body){
        Note note = noteService.getById(id);
        NoteImage noteImage = new NoteImage();
        noteImage.setNote(note);
        noteImage.setBase64Image(body.getImg());
        NoteImage saved = noteImageService.save(noteImage);
        return saved.getId().toString();
    }
    @GetMapping("/notes/{id}/image/{image_id}")
    public ResponseEntity<Resource> getImage(@PathVariable("id") Long id,
                                             @PathVariable("image_id") UUID imageId){
        Note note = noteService.getById(id);
        NoteImage noteImage = noteImageService.getByNoteAndUUID(imageId, note);
        String imageString = noteImage.getBase64Image().replaceFirst("data:image/jpeg;base64,", "");

        // Декодируем Base64 строку
        byte[] imageBytes = Base64.getDecoder().decode(imageString);

        // Создаем ресурс из массива байтов
        ByteArrayResource resource = new ByteArrayResource(imageBytes);

        // Возвращаем ресурс в ResponseEntity
        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=image.jpeg")
                .contentLength(imageBytes.length)
                .contentType(org.springframework.http.MediaType.IMAGE_JPEG)
                .body(resource);
    }

    @PostMapping("/notes")
    public NoteDto createNote(@RequestBody CreateNoteDto dto){
        Note note = new Note(dto, folderService.getById(dto.getFolder_id()));
            return new NoteDto(noteService.save(note));
    }

    @PatchMapping("/notes")
    public NoteDto updateNote(@RequestBody CreateNoteDto dto, @RequestParam("id") Long id){
        Note note = new Note(id, dto.getTitle(), dto.getContent(), folderService.getById(dto.getFolder_id()));
        return new NoteDto(noteService.save(note));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/notes")
    public void deleteNote(@RequestParam("id") Long id){
        noteService.delete(id);
    }

}
