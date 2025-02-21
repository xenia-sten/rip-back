package ru.stenyaeva.back.model.note;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;

import java.sql.Timestamp;
import java.util.UUID;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class NoteImage {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String base64Image;

    @ManyToOne
    private Note note;

    @CreationTimestamp
    private Timestamp createdAt;


}
