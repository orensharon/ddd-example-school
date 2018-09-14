package com.sharono.example.ddd.school.domain;

import java.util.List;

public class Grade {

    private final int level; //1-12

    private final Teacher supervisor;
    private final List<ClassRoom> classRooms;

    public Grade(int level, Teacher supervisor, List<ClassRoom> classRooms) {
        this.level = level;
        this.supervisor = supervisor;
        this.classRooms = classRooms;
    }

    public int getLevel() {
        return level;
    }

    public Teacher getSupervisor() {
        return supervisor;
    }

    public List<ClassRoom> getClassRooms() {
        return classRooms;
    }
}
