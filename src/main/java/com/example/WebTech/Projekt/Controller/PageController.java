package com.example.WebTech.Projekt.Controller;

import com.example.WebTech.Projekt.Page.Page;
import com.example.WebTech.Projekt.Page.PageService;
import com.example.WebTech.Projekt.Request.NoteRequest;
import com.example.WebTech.Projekt.User.User;
import com.example.WebTech.Projekt.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PageController {

    @Autowired
    PageService service;
    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(PageController.class);

    @PostMapping("/create-page/{ownerId}")
    public Page createPage(@RequestBody NoteRequest noteRequest, @PathVariable String ownerId) {
            User user = userService.getUser(Long.valueOf(ownerId));
            Page newPage = new Page(noteRequest.getString(), user);
            return service.save(newPage);

    }

    @GetMapping("/page/{id}")
    public Page getPage(@PathVariable String id) {
        logger.info("GET request on route notes with {}", id);
        Long pageId = Long.parseLong(id);
        return service.get(pageId);
    }

    @GetMapping("/pagebyowner/{ownerId}")
    public List<Page> getAllPages(@PathVariable("ownerId") Long id) {
        return service.getAll(id);
    }
    @DeleteMapping("/deletepage/{id}")
    public String deletePageById(@PathVariable("id") String id){
        service.deleteById(Long.valueOf(id));
        System.out.println("delete by id");
        return "Delete by id";
    }

}
