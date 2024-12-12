package ru.stenyaeva.back.domain.dto.note;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNoteDto {
    private String title;
    private String content;
    private Long folder_id;

    @JsonCreator
    public CreateNoteDto(@JsonProperty("title") String title, @JsonProperty("content") String content, @JsonProperty("folder_id") Long folder_id){
        this.title = title;
        this.content = content;
        this.folder_id = folder_id;
    }
}
