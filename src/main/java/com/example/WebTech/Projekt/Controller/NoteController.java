package com.example.WebTech.Projekt.Controller;

import com.example.WebTech.Projekt.Note.Note;
import com.example.WebTech.Projekt.Note.NoteRequest;
import com.example.WebTech.Projekt.Note.NoteService;
import com.example.WebTech.Projekt.Page.Page;
import com.example.WebTech.Projekt.Page.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

        @Autowired
        NoteService service;
        @Autowired
        PageService pageService;


        Logger logger = LoggerFactory.getLogger(NoteController.class);

        @PostMapping("/page/note/{pageId}")
        public Note createNote(@RequestBody NoteRequest noteRequest, @PathVariable("pageId") String pageId) {
            Long pageIdValue = Long.parseLong(pageId);
            Page page = pageService.get(pageIdValue);
            Note note = new Note(noteRequest.getString(), page);
            System.out.println(note);
            return service.save(note);
        }

        @GetMapping("/note/{id}")
        public Note getNote(@PathVariable String id) {
            logger.info("GET request on route notes with {}", id);
            Long noteId = Long.parseLong(id);
            return service.get(noteId);
        }

    @GetMapping("/page/notes/{pageId}")
    public List<Note> getNotesFromPage(@PathVariable String pageId) {
        logger.info("GET request on route notes with {}", pageId);
        return service.getAllFromPage(Long.valueOf(pageId));
    }

        @GetMapping("/page/note")
        public List<Note> getAllNotes() {
            return service.getAll();
        }
        @DeleteMapping("/page/note/{id}")
        public String deleteNoteById(@PathVariable("id") Long id){
            service.deleteById(id);
            System.out.println("delete by id");
            return "Delete by id";
        }

    }

