package ru.stenyaeva.back.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.stenyaeva.back.model.note.Note;
import ru.stenyaeva.back.model.user.User;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        noteRepository.deleteAll();
        userRepository.deleteAll();
        User egor = userRepository.save(new User(null, "Egor","ekorovin03@gmail.com",encoder.encode("12345678"), List.of()));
        User kseniya = userRepository.save(new User(null, "Kseniya","stenyeva03@gmail.com",encoder.encode("12345678"), List.of()));
        Note note1 = new Note(null, "note1",egor);
        Note note2 = new Note(null, "note2",egor);
        Note note3 = new Note(null, "note3",kseniya);
        Note note4 = new Note(null, "note4",kseniya);
        noteRepository.saveAll(List.of(note1, note2, note3, note4));
    }

}
