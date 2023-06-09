package com.example.notepadapp;

public class Note {
    private int id;
    private String title;
    private String subtitle;
    private String body;

    public Note() {
        // Default constructor
    }

    public Note(String title, String subtitle, String body) {
        this.title = title;
        this.subtitle = subtitle;
        this.body = body;
    }

    public Note(int id, String title, String subtitle, String body) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.body = body;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
