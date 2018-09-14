package com.sharono.example.ddd.school.domain;

import com.sharono.example.ddd.school.Constants;

import java.util.List;

public class Grade {

    private final int level;
    private final Teacher supervisor;
    private final List<ClassRoom> classRooms;

    public Grade(int level, Teacher supervisor, List<ClassRoom> classRooms) {
        if (supervisor == null || classRooms == null) {
            throw new NullPointerException();
        }
        if (level < Constants.MIN_GRADE || level > Constants.MAX_GRADE) {
            throw new IllegalArgumentException(String.format("Illegal grade level: %d", level));
        }
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
