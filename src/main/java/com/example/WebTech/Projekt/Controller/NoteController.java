package com.example.WebTech.Projekt.Controller;

import com.example.WebTech.Projekt.Note.Note;
import com.example.WebTech.Projekt.Note.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {
        @Autowired
        NoteService service;

        Logger logger = LoggerFactory.getLogger(NoteController.class);

        @PostMapping("/note")
        public Note createNote(@RequestBody Note note) {
            return service.save(note);
        }

        @GetMapping("/note/{id}")
        public Note getNote(@PathVariable String id) {
            logger.info("GET request on route notes with {}", id);
            Long noteId = Long.parseLong(id);
            return service.get(noteId);
        }

        @GetMapping("/note")
        public List<Note> getAllNotes() {
            return service.getAll();
        }

    }
