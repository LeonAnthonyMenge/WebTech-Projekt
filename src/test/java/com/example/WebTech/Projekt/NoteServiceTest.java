package com.example.WebTech.Projekt;

import com.example.WebTech.Projekt.Note.Note;
import com.example.WebTech.Projekt.Note.NoteRepository;
import com.example.WebTech.Projekt.Note.NoteService;
import com.example.WebTech.Projekt.Page.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class NoteServiceTest {
    @Autowired
    private NoteService service;

    @MockBean
    private NoteRepository repository;

    @Test
    @DisplayName("should find Note by its id")
    public void testFindById(){
        Long id = 1L;
        Page page = new Page();
        var n1 = new Note("findById", page);
        doReturn(n1).when(repository).findById(id);

        Note result = service.get(id);

        assertEquals(result, n1);
    }

    @Test
    @DisplayName("should find all Notes")
    public void testFindAllNotes(){
        Page page = new Page();
        var n1 = new Note("findAllNotes", page);
        var n2 = new Note("findAllNotes2", page);

        List<Note> expected = new ArrayList<>();
        expected.add(n1);
        expected.add(n2);
        doReturn(expected).when(repository).findAll();


        List<Note> result =  service.getAll();

        assertEquals(result, expected);
    }

    @Test
    @DisplayName("should find all Notes")
    public void testGetAllFromPage(){
        Page page = new Page();
        var n1 = new Note("FindAllFromPage", page);
        var n2 = new Note("FindAllFromPage", page);

        List<Note> expected = new ArrayList<>();
        expected.add(n1);
        expected.add(n2);
        doReturn(expected).when(repository).findAll();

        List<Note> result =  service.getAllFromPage(1L);

        assertEquals(result, expected);
    }
}
