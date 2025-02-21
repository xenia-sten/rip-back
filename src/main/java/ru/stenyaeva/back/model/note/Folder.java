package ru.stenyaeva.back.model.note;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.stenyaeva.back.domain.dto.folder.CreateFolderDto;
import ru.stenyaeva.back.domain.dto.folder.FolderDto;
import ru.stenyaeva.back.model.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "folders")
@Entity
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "owner_id")
    @ManyToOne
    private User owner;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Folder parent;  // Ссылка на родительскую папку

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
//    public Folder(FolderDto dto, User owner){
//        this.id = dto.getId();
//        this.name = dto.getName();
//        this.owner = owner;
//        this.parent = folder dto.getParentId()
//    }

    public Folder (CreateFolderDto dto, User owner, Folder parent){
        this.name = dto.getName();
        this.parent = parent;
        this.owner = owner;
    }

    public Folder(Long id, String name, User owner, Folder parent) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.parent = parent;
    }
}
