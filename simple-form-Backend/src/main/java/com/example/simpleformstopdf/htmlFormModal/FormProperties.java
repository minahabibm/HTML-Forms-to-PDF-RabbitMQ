package com.example.simpleformstopdf.htmlFormModal;

import java.io.Serializable;

public class FormProperties implements Serializable {

    private String id;
    private String name;
    private String title;
    private String quote;
    private String file;

    // Default constructor is needed to de-serialize JSON
    public FormProperties() {
    }

    public FormProperties(String id, String name, String title, String quote, String file) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.quote = quote;
        this.file = file;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
