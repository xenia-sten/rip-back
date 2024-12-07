package ru.stenyaeva.back.model.note;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stenyaeva.back.domain.dto.note.CreateNoteDto;
import ru.stenyaeva.back.domain.dto.note.NoteDto;
import ru.stenyaeva.back.model.user.User;

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

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "owner_id")
    @ManyToOne
    private User owner;

    public Note(NoteDto dto, User owner){
        this.id = dto.getId();
        this.name = dto.getName();
        this.owner = owner;
    }

    public Note(CreateNoteDto dto, User owner){
        this.name = dto.getName();
        this.owner = owner;
    }
}
