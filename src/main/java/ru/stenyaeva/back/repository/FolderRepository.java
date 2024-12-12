package ru.stenyaeva.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stenyaeva.back.model.note.Folder;
import ru.stenyaeva.back.model.user.User;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findAllByOwner(User owner);
    List<Folder> findAllByParent(Folder parent);
}
