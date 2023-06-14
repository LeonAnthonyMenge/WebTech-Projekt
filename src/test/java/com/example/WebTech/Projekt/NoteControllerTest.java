package com.example.WebTech.Projekt;

import com.example.WebTech.Projekt.Controller.NoteController;
import com.example.WebTech.Projekt.Note.Note;
import com.example.WebTech.Projekt.Note.NoteService;
import com.example.WebTech.Projekt.Page.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Tests Route of Note")
    public void testGetRoute() throws Exception {
        // Testdaten und Service Mock
        Page page = new Page();
        Note p1 = new Note("TestNote", page);
        p1.setId(42L);
        when(service.get(42L)).thenReturn(p1);


        // Erwartetes Ergebnis
        String expected = "{\"id\":42,\"done\":false,\"name\":\"TestNote\"}";

        // Aufruf und Vergleich
        mockMvc.perform(get("/note/42"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }


    @Test
    @DisplayName("Tests Route of Note")
    public void testGetRoute2() throws Exception {
        // Testdaten und Service Mock
        Page page = new Page("testGetRoute");
        Note n1 = new Note("testNote", page);
        n1.setId(42L);
        doReturn(Optional.of(n1)).when(service.get(42L));

        // Erwartetes Ergebnis
        String expected = "{\"id\":42,\"done\":false,\"name\": \"testNote\"}";

        // Aufruf und Vergleich
        mockMvc.perform(get("/note/42"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }





}
