package com.example.WebTech.Projekt;

import com.example.WebTech.Projekt.Note.Note;
import com.example.WebTech.Projekt.Page.Page;
import com.example.WebTech.Projekt.Page.PageRepository;
import com.example.WebTech.Projekt.Page.PageService;
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
public class PageServiceTest {
    @Autowired
    private PageService service;
    @MockBean
    private PageRepository repository;

    @Test
    @DisplayName("should find Page by its id")
    public void testFindById(){
        Long id = 1L;
        var n1 = new Page("findById");
        doReturn(Optional.of(n1)).when(repository).findById(id);

        Page result = service.get(id);

        assertEquals(result.getName(), n1.getName());
    }

    @Test
    @DisplayName("should find all Pages")
    public void testFindAllPages(){
        var p1 = new Page("findAllPages");
        var p2 = new Page("findAllPages2");

        List<Page> expected = new ArrayList<>();
        expected.add(p1);
        expected.add(p2);
        doReturn(expected).when(repository).findAll();

        List<Page> result =  service.getAll();

        assertEquals(result.get(1).getName(), expected.get(1).getName());
    }
}