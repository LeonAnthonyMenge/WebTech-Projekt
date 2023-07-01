package com.example.WebTech.Projekt.Page;

import com.example.WebTech.Projekt.Note.Note;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "PAGE")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name="ownwer")
    private String owner;

    @OneToMany(
            mappedBy = "page",
            cascade = REMOVE,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Note> notes;

    public Page(String name, String owner) {
        this.name = name;
        this.owner = owner;
        this.notes = new ArrayList<>();
    }
    //Constructur for Pages without Owners
    public Page(String name) {
        this.name = name;
        this.notes = new ArrayList<>();
    }
    public Page() {

    }

    @JsonManagedReference
    public List<Note> getNotes() { return notes; }

    public String getUser(){ return owner; }

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
