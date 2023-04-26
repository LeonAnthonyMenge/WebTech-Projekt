package com.example.WebTech.Projekt.Note;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Note {
    // Aus Github von Prof Wider und abgeändert zu Note Klasse
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String string;
    private boolean done = false;

    public Note(String note) {
        this.string = note;
    }

    public Note() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return string;
    }

    public void setString(String note) {
        this.string = note;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
