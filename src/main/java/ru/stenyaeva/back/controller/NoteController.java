package ru.stenyaeva.back.controller;

import jakarta.servlet.annotation.HttpConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.stenyaeva.back.domain.dto.note.CreateNoteDto;
import ru.stenyaeva.back.domain.dto.note.NoteDto;
import ru.stenyaeva.back.domain.utils.SecurityUtils;
import ru.stenyaeva.back.model.note.Note;
import ru.stenyaeva.back.service.NoteService;

import java.util.List;

@RequestMapping("/notes")
@RestController
@RequiredArgsConstructor
public class NoteController {
    private final SecurityUtils securityUtils;
    private final NoteService noteService;

    @GetMapping
    public List<NoteDto> getAll(){
        return noteService.getAllByOwner(securityUtils.getUser()).stream().map(NoteDto::new).toList();//{
    }

    @PostMapping
    public NoteDto createNote(@RequestBody CreateNoteDto dto){
        Note note = new Note(dto, securityUtils.getUser());
        return new NoteDto(noteService.save(note));
    }

    @PatchMapping
    public NoteDto updateNote(@RequestBody NoteDto dto){
        Note note = new Note(dto, securityUtils.getUser());
        return new NoteDto(noteService.save(note));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteNote(@RequestParam("id") Long id){
        noteService.delete(id);
    }


}
