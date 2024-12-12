package ru.stenyaeva.back.domain.dto.note;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import ru.stenyaeva.back.model.note.Note;

import java.util.Base64;

@Getter
public class NoteDto {
    private Long id;
    private String title;
    private String content;
    private Long folder_id;

    @JsonCreator
    public NoteDto(@JsonProperty("id") Long id,
                   @JsonProperty("title") String title,
                   @JsonProperty("content") String content,
                   @JsonProperty("folder_id") Long folder_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.folder_id = folder_id;
    }

    public NoteDto(Note note){
        this.id = note.getId();
        this.title = note.getTitle();
        this.content = note.getContent();
    }
}
