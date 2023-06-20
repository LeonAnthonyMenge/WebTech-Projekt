package com.example.WebTech.Projekt;

import com.example.WebTech.Projekt.Controller.PageController;
import com.example.WebTech.Projekt.Page.Page;
import com.example.WebTech.Projekt.Page.PageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PageController.class)
public class PageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PageService service;

    @Test
    @DisplayName("should return page by id")
    public void testGetRoute() throws Exception {
        // Testdaten und Service Mock
        Page p1 = new Page("testGetRoute");
        p1.setId(42L);
        when(service.get(42L)).thenReturn(p1);

        // Erwartetes Ergebnis
        String expected = "{\"id\":42,\"name\":\"testGetRoute\",\"notes\": []}";

        // Aufruf und Vergleich
        mockMvc.perform(get("/page/42"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }
    @Test
    @DisplayName("should delete page by id")
    public void testDeleteRoute() throws Exception {
        // Testdaten und Service Mock
        Page p1 = new Page("testGDeleteRoute");
        p1.setId(42L);


        // Aufruf und Vergleich
        mockMvc.perform(delete("/page/42"))
                .andExpect(status().isOk());
    }
}