package com.sharono.example.ddd.school.domain;

import java.util.List;

public class ClassRoom {

    private final int id;
    private final List<Student> students;
    private final Teacher teacher;

    public ClassRoom(int id, List<Student> students, Teacher teacher) {
        if (students == null || teacher == null) {
            throw new NullPointerException();
        }
        this.id = id;
        this.students = students;
        this.teacher = teacher;
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
}
