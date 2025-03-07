package ru.stenyaeva.back.model.note;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.stenyaeva.back.domain.dto.note.CreateNoteDto;

import java.sql.Timestamp;
import java.util.List;

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


    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "note")
    private List<NoteImage> images;


    public Note(Long id, String title, String content, Folder folder) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.folder = folder;
    }

    public Note(CreateNoteDto dto, Folder folder){
        this.title = dto.getTitle();
        this.content = dto.getContent() != null ? dto.getContent() : "";
        this.folder = folder;
    }
}
