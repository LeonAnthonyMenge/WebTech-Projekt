package com.example.WebTech.Projekt.Note;

import com.example.WebTech.Projekt.Page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

        @Autowired
        NoteRepository repo;

        public Note save(Note note) {
            Page page = note.getPage();
            page.addNote(note);
            return repo.save(note);
        }

        public Note get(Long id) {
            return repo.findById(id).orElseThrow(() -> new RuntimeException());
        }

        public List<Note> getAll() {
            Iterable<Note> iterator = repo.findAll();
            List<Note> notes = new ArrayList<Note>();
            for (Note note : iterator)  notes.add(note);
            return notes;
        }

        public void delete(Note note){
                repo.delete(note);
        }

        public List<Note> getAllFromPage(Long pageId) {
        Iterable<Note> iterator = repo.findAll();
            List<Note> notes = new ArrayList<Note>();
        for (Note note : iterator) {
            if (note.getPage().getId() == pageId) {
                notes.add(note);
            }
        }
        return notes;
    }

    public boolean deleteById(Long noteId) {
        try {
            Note note = get(noteId);
            Page page = note.getPage();
            page.deleteNotes(note);
            repo.delete(note);
            return true; // Das Löschen war erfolgreich
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Fehler beim Löschen der Notiz
        }
    }
}
