package com.sharono.example.ddd.school.domain;

import java.util.List;

public class School {

    private final int id;
    private final String name;
    private final String address;

    private final Teacher director;
    private final List<Grade> grades;

    public School(int id, String name, String address, Teacher director, List<Grade> grades) {
        if (director == null || grades == null || name == null || address == null) {
            throw new NullPointerException();
        }
        if (name.trim().equals("")) {
            throw new IllegalArgumentException(String.format("Illegal school name: %s", name));
        }
        if (address.trim().equals("")) {
            throw new IllegalArgumentException(String.format("Illegal school address: %s", address));
        }
        this.id = id;
        this.name = name;
        this.address = address;
        this.director = director;
        this.grades = grades;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Teacher getDirector() {
        return director;
    }

    public List<Grade> getGrades() {
        return grades;
    }
}
