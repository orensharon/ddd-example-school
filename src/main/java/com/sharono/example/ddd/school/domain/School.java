package com.sharono.example.ddd.school.domain;

import java.util.List;

public class School {

    private final int id;
    private final String name;
    private final String address;

    private final Teacher director;
    private final List<Grade> grades;

    public School(int id, String name, String address, Teacher director, List<Grade> grades) {
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
