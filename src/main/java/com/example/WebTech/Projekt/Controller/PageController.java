package com.example.WebTech.Projekt.Controller;

import com.example.WebTech.Projekt.Page.Page;
import com.example.WebTech.Projekt.Page.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PageController {

    @Autowired
    PageService service;

    Logger logger = LoggerFactory.getLogger(PageController.class);

    @PostMapping("/page")
    public ResponseEntity<?> createPage(@RequestBody Page page) {
        try {
            Page createdPage = service.save(page);
            return ResponseEntity.ok(createdPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fehler beim Erstellen der Seite: " + e.getMessage());
        }
    }

    @GetMapping("/page/{id}")
    public Page getPage(@PathVariable String id) {
        logger.info("GET request on route notes with {}", id);
        Long pageId = Long.parseLong(id);
        return service.get(pageId);
    }

    @GetMapping("/page")
    public List<Page> getAllPages() {
        return service.getAll();
    }
    @DeleteMapping("/page/{id}")
    public String deletePageById(@PathVariable("id") String id){
        service.deleteById(Long.valueOf(id));
        System.out.println("delete by id");
        return "Delete by id";
    }

}
