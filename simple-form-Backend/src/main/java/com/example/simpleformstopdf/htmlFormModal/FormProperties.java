package com.example.simpleformstopdf.htmlFormModal;

public class FormProperties {
    private String id;
    private String firstName;
    private String lastName;
    private String quote;
    private String file;

    public FormProperties(String id, String firstName, String lastName, String quote, String file) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.quote = quote;
        this.file = file;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
