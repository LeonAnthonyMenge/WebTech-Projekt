package com.example.WebTech.Projekt.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageService {

    @Autowired
    PageRepository repo;

    public Page save(Page page) {
        return repo.save(page);
    }

    public Page get(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public List<Page> getAll() {
        Iterable<Page> iterator = repo.findAll();
        List<Page> pages = new ArrayList<>();
        for (Page page : iterator)  pages.add(page);
        return (List<Page>) repo.findAll();
    }

    public void deleteById(Long id){
        try {
            repo.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("deletion failed:    " + e);
        }
    }
}
