package com.example.WebTech.Projekt.Page;

import com.example.WebTech.Projekt.User.User;
import com.example.WebTech.Projekt.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PageService {

    @Autowired
    PageRepository repo;
    @Autowired
    UserRepository userRepository;

    public Page save(Page page) {
        return repo.save(page);
    }

    public Page get(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public List<Page> getAll(Long id) {
        Iterable<Page> iterator = repo.findAll();
        List<Page> pages = new ArrayList<>();
        for (Page page : iterator)
            if(page.getOwner() != null){
                if(page.getOwner().getId() == id){
                    pages.add(page);
                }
            }
        return pages;
    }

    public void deleteById(Long id){
        try {
            repo.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("deletion failed:    " + e);
        }
    }
}
