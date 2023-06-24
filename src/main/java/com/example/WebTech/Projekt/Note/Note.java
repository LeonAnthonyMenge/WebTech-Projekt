package com.example.WebTech.Projekt.Note;


import com.example.WebTech.Projekt.Page.Page;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Note")
public class Note {
    // Aus Github von Prof Wider und abgeändert zu Note Klasse
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "string")
    private String string;
    @Column(name = "done")
    private boolean done = false;

    @ManyToOne(fetch = FetchType.EAGER)
    //@MapsId("pageId")
    @JoinColumn(name = "page_id")
    private Page page;

    public Note(String note, Page page) {
        this.string = note;
        this.page = page;
    }

    public Note() {

    }
    @JsonBackReference
    public Page getPage() {return page;}
    public void setPage(Page page) {
        this.page = page;
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

    public void setId(long id) {
        this.id = id;
    }
}
