package com.example.WebTech.Projekt.Controller;

import com.example.WebTech.Projekt.Page.Page;
import com.example.WebTech.Projekt.Page.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PageController {

    @Autowired
    PageService service;

    Logger logger = LoggerFactory.getLogger(PageController.class);

    @PostMapping("/page/{owner}")
    public ResponseEntity<?> createPage(@PathVariable String owner, @RequestBody Page page) {
        try {
            Page savePage = new Page(page.getName(), owner);
            Page createdPage = service.save(savePage);
            return ResponseEntity.ok(createdPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fehler beim Erstellen der Seite: " + e.getMessage());
        }
    }

    @GetMapping("/page/{id}/{owner}")
    public Page getPage(@PathVariable String owner, @PathVariable String id) {
        logger.info("GET request on route notes with {}", id);
        Long pageId = Long.parseLong(id);
        Page page = service.get(pageId);
        if(page.getUser().equals(owner)){
            return page;
        }else{
            return null;
        }
    }

    @GetMapping("/page/{owner}")
    public List<Page> getAllPages(@PathVariable String owner)
    {
        return service.getAll(owner);
    }

    @DeleteMapping("/page/{id}/{owner}")
    public String deletePageById(@PathVariable String owner, @PathVariable("id") String id){
        if(owner.equals(service.get(Long.valueOf(id)).getUser())){
            service.deleteById(Long.valueOf(id));
            System.out.println("delete by id");
            return "Delete by id";
        } else {
            return null;
        }

    }

}
