package ru.stenyaeva.back.domain.dto.note;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import ru.stenyaeva.back.model.note.Note;

@Getter
public class NoteDto {
    private Long id;
    private String name;

    @JsonCreator
    public NoteDto(@JsonProperty("id") Long id,
                   @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public NoteDto(Note note){
        this.id = note.getId();
        this.name = note.getName();
    }
}
