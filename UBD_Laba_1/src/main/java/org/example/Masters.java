package org.example;

public class Masters {
    private int id;
    private String surname;
    private String name;
    public Masters(int id, String surname, String name) {
        this.id = id;
        this.surname = surname;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurnameName() {
        return surname;
    }

    public void setSurnametName(String surname) {
        this.surname = surname;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
