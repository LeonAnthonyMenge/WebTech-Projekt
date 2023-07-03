package com.example.WebTech.Projekt.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(User user){ return userRepository.save(user); }

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public List<User> getAll(){
        Iterable<User> iterator = userRepository.findAll();
        List<User> users = new ArrayList<>();
        for (User user : iterator)  users.add(user);
        return users;
    }

    public User findByEmail(String email){
        Iterable<User> iterator = userRepository.findAll();
        List<User> users = new ArrayList<>();
        for (User user : iterator){
            if( user.getEmail().equals(email)){
                return user;
            }
        }
        return  null;
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
