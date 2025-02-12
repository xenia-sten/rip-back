package ru.stenyaeva.back.controller;

import jakarta.servlet.annotation.HttpConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.stenyaeva.back.domain.dto.note.CreateNoteDto;
import ru.stenyaeva.back.domain.dto.note.NoteDto;
import ru.stenyaeva.back.domain.utils.SecurityUtils;
import ru.stenyaeva.back.model.note.Note;
import ru.stenyaeva.back.service.FolderService;
import ru.stenyaeva.back.service.NoteService;

import java.util.List;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class NoteController {
    private final SecurityUtils securityUtils;
    private final NoteService noteService;
    private final FolderService folderService;

    @GetMapping("/folders/{id}/notes")
    public List<NoteDto> getAll(@PathVariable("id") Long id){
        return noteService.getAllByFolder(folderService.getById(id)).stream().map(NoteDto::new).toList();//{
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
