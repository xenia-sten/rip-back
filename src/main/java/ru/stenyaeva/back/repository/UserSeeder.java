package ru.stenyaeva.back.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.stenyaeva.back.domain.dto.note.CreateNoteDto;
import ru.stenyaeva.back.model.note.Folder;
import ru.stenyaeva.back.model.note.Note;
import ru.stenyaeva.back.model.user.User;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;
    private final FolderRepository folderRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        noteRepository.deleteAll();
        folderRepository.deleteAll();
        userRepository.deleteAll();

        User egor = userRepository.save(new User(null, "Egor","ekorovin03@gmail.com",encoder.encode("12345678"), List.of()));
        User kseniya = userRepository.save(new User(null, "Kseniya","stenyeva03@gmail.com",encoder.encode("12345678"), List.of()));
//        Note note1 = new Note(null, "note1",egor);
//        Note note2 = new Note(null, "note2",egor);
//        Note note3 = new Note(null, "note3",kseniya);
//        Note note4 = new Note(null, "note4",kseniya);
//        noteRepository.saveAll(List.of(note1, note2, note3, note4));

        Folder folder1 = new Folder(null, "folder1", kseniya, null);
        Folder folder2 = new Folder(null, "folder2", kseniya, null);
        Folder folder3 = new Folder(null, "folder3", kseniya, null);
//        Folder folder2 = new Folder(null, "folder2", kseniya, folder1,null);
        folderRepository.saveAll(List.of(folder1, folder2, folder3));
//        folder1.setParent(folder2);
//        folder3.setParent(folder2);
//        folderRepository.save(folder1);
//        folderRepository.save(folder3);
        CreateNoteDto dto = new CreateNoteDto("title", "UEsDBAoAAAAAABxJjFkyzPruGAAAABgAAAAHAAAAbm90ZS5tZFBHZ3hQazE1SUU1dmRHVThMMmd4UGc9PVBLAQIUAAoAAAAAABxJjFkyzPruGAAAABgAAAAHAAAAAAAAAAAAAAAAAAAAAABub3RlLm1kUEsFBgAAAAABAAEANQAAAD0AAAAAAA==", folder1.getId());
        Note note = new Note(dto, folder1);
        noteRepository.save(note);
    }

}
