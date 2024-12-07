package ru.stenyaeva.back.domain.dto.note;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNoteDto {
    private String name;

    @JsonCreator
    public CreateNoteDto(@JsonProperty("name") String name){
        this.name = name;
    }
}
