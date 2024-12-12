package ru.stenyaeva.back.model.note;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stenyaeva.back.domain.dto.note.CreateNoteDto;
import ru.stenyaeva.back.domain.dto.note.NoteDto;
import ru.stenyaeva.back.model.user.User;

import java.util.Base64;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "notes")
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @JoinColumn(name = "folder_id")
    @ManyToOne
    private Folder folder;


//    public Note(NoteDto dto, Folder folder){
//        this.id = dto.getId();
//        this.title = dto.getTitle();
//        this.content = dto.getContent() != null ? Base64.getDecoder().decode(dto.getContent()) : new byte[0];
//        this.folder = folder;
//    }

    public Note(CreateNoteDto dto, Folder folder){
        this.title = dto.getTitle();
        this.content = dto.getContent() != null ? dto.getContent() : "";
        this.folder = folder;
    }
}
