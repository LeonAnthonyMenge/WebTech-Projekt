package com.example.WebTech.Projekt.Page;

import com.example.WebTech.Projekt.Note.Note;
import com.example.WebTech.Projekt.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "PAGE")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(
            mappedBy = "page",
            cascade = REMOVE,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Note> notes;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    public Page(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.notes = new ArrayList<>();
    }
    public Page() {

    }

    @JsonManagedReference
    public List<Note> getNotes() { return notes; }
    @JsonBackReference
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void deleteNotes(Note note) {
        this.notes.remove(note);
        note.setPage(null);
    }

    public void addNote(Note note) {
     this.notes.add(note);
     }


}
