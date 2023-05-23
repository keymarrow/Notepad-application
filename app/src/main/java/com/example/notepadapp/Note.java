package com.example.notepadapp;

public class Note {
    private int id;
    private String title;
    private String subtitle;

    public Note() {
        // Default constructor
    }

    public Note(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public Note(int id, String title, String subtitle) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }


    public int getId() {
        return id;
    }
}
