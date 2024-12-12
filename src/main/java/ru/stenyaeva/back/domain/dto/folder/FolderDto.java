package ru.stenyaeva.back.domain.dto.folder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import ru.stenyaeva.back.model.note.Folder;
import ru.stenyaeva.back.model.note.Note;

@Getter
public class FolderDto {
    private Long id;
    private String name;
    private Long parentId;

    @JsonCreator
    public FolderDto(@JsonProperty("id") Long id,
                   @JsonProperty("name") String name,
                     @JsonProperty("parentId") Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public FolderDto(Folder folder){
        this.id = folder.getId();
        this.name = folder.getName();
        this.parentId = folder.getParent() == null ? null : folder.getParent().getId();
    }
}
