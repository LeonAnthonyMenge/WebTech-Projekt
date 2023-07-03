package com.example.WebTech.Projekt.User;

import com.example.WebTech.Projekt.Page.Page;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;


import static jakarta.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "Note_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "password")
    private String hashedPassword;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "salt")
    private String salt;

    @OneToMany(
            mappedBy = "owner",
            cascade = REMOVE,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Page> pages;

    public User(String firstname, String lastname, String password, String email){
        this.firstname = firstname;
        this.lastname = lastname;
        setPassword(password);
        this.email = email;
    }

    public User() {

    }

    private void setPassword(String password) {
        this.salt = generateSalt();
        this.hashedPassword = hashPassword(password, salt);
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }


    public boolean isPasswordValid(String password) {
        String hashedInput = hashPassword(password, salt);
        return hashedPassword.equals(hashedInput);
    }

    private String hashPassword(String password, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] saltBytes = Base64.getDecoder().decode(salt);
            digest.reset();
            digest.update(saltBytes);
            byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password.", e);
        }
    }


    @JsonManagedReference
    public List<Page> getPages() {
        return pages;
    }

    public void addPage(Page page){
        pages.add(page);
    }

    public void deletePage(Page page){
        this.pages.remove(page);
        page.setOwner(null);
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
