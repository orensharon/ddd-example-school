package com.sharono.example.ddd.school.domain;

import java.util.List;

import static com.sharono.example.ddd.school.School.ClassRoomState.CLOSED;
import static com.sharono.example.ddd.school.School.ClassRoomState.OPENED;

public class ClassRoom {

    private final int id;
    private final List<Student> students;
    private final Teacher teacher;

    private int state;

    public ClassRoom(int id, List<Student> students, Teacher teacher, int state) {
        if (students == null || teacher == null) {
            throw new NullPointerException();
        }
        this.id = id;
        this.students = students;
        this.teacher = teacher;
        this.state = state;
    }

    public void open() {
        if (this.state == OPENED) {
            throw new RuntimeException("Illegal command - already opened");
        }
        this.state = OPENED;
        // TODO: raise event
    }

    public void close() {
        if (this.state == CLOSED) {
            throw new RuntimeException("Illegal command - already closed");
        }
        this.state = CLOSED;
        // TODO: raise event
    }

    public int getId() {
        return id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public boolean isOpened() {
        return this.state == OPENED;
    }

    public boolean isClosed() {
        return this.state == CLOSED;
    }
}
