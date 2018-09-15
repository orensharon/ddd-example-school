package com.sharono.example.ddd.school.domain.class_room;

import com.sharono.example.ddd.school.domain.Aggregate;
import com.sharono.example.ddd.school.domain.student.Student;
import com.sharono.example.ddd.school.domain.teacher.Teacher;

import java.util.List;

public class ClassRoom extends Aggregate {

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
