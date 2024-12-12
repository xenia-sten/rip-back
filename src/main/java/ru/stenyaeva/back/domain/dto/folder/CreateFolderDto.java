package ru.stenyaeva.back.domain.dto.folder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.stenyaeva.back.model.note.Folder;

@Getter
@Setter
public class CreateFolderDto {
    private String name;
    private Long parent_id;
    @JsonCreator
    public CreateFolderDto(@JsonProperty("name") String name,
                           @JsonProperty("parent_id") Long parent_id){
        this.name = name;
        this.parent_id = parent_id;
    }
}
