package pl.deen.notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.deen.notes.model.Note;
import pl.deen.notes.repository.NoteRepo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private NoteRepo noteRepo;

    @Autowired
    public NoteService(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    public Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public Note addNote(Note note) {
        return noteRepo.save(note);
    }

    public void editNote(Long id, Note editedNote) {
        Optional<Note> optionalNote = findNoteById(id);
        if (optionalNote.isPresent()) {
            if (!optionalNote.get().getTitle().equals(editedNote.getTitle())) {
                optionalNote.get().setTitle(editedNote.getTitle());
            }
            if (!optionalNote.get().getContent().equals(editedNote.getContent())) {
                optionalNote.get().setContent(editedNote.getContent());
            }
            noteRepo.save(optionalNote.get());
        }
    }

    public Optional<Note> findNoteById(Long id) {
        return noteRepo.findById(id);
    }

    public boolean deleteNoteById(Long id) {
        noteRepo.deleteById(id);
        Optional<Note> optionalNote = findNoteById(id);
        if (optionalNote.isPresent()) {
            return false;
        }
        return true;
    }

    public List<Note> findAllNotes() {
        return noteRepo.findAll();
    }
}
