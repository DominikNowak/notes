package pl.deen.notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.deen.notes.model.Note;
import pl.deen.notes.service.NoteService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class NoteController {
    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String showAll(Model model) {
        List<Note> notesList = noteService.findAllNotes();
        model.addAttribute("notesList", notesList);
        return "mainView";
    }

    @GetMapping("/add")
    public String addNoteGet(Model model) {
        model.addAttribute("newNote", new Note());
        return "addNote";
    }

    @PostMapping("/add")
    public String addNotePost(@ModelAttribute Note newNote, HttpServletRequest request) {
        newNote.setTimestamp(noteService.getTimestamp());
        newNote.setRemoteAddr(request.getRemoteAddr());
        noteService.addNote(newNote);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable("id") Long id) {
        noteService.deleteNoteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editNoteGet(@PathVariable("id") Long noteId, Model model) {
        model.addAttribute("oldNote", noteService.findNoteById(noteId).get());
        return "editNote";
    }

    @PostMapping("/edit/{id}")
    public String editNotePost(@ModelAttribute("oldNote") Note editedNote, @PathVariable("id") Long id) {
        noteService.editNote(id, editedNote);
        return "redirect:/";
    }


}
