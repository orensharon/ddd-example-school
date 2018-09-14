package com.sharono.example.ddd.school.domain.class_room;

import com.sharono.example.ddd.school.domain.Aggregate;
import com.sharono.example.ddd.school.domain.student.Student;
import com.sharono.example.ddd.school.domain.teacher.Teacher;

import java.util.List;

import static com.sharono.example.ddd.school.School.ClassRoomState.*;

public class ClassRoom extends Aggregate {

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
        if (this.state == OPENING) {
            throw new RuntimeException("Illegal command - already opening");
        }
        this.state = OPENING;
        // TODO: raise event
    }

    public void close() {
        if (this.state == CLOSED) {
            throw new RuntimeException("Illegal command - already closed");
        }
        if (this.state == CLOSING) {
            throw new RuntimeException("Illegal command - already closing");
        }
        this.state = CLOSING;
        // TODO: raise event
    }

    public void opened() {
        if (this.isOpening()) {
            this.state = OPENED;
            // TODO: raise event
        }
    }

    public void closed() {
        if (this.isClosing()) {
            this.state = CLOSED;
            // TODO: raise event
        }
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

    public boolean isOpening() {
        return this.state == OPENING;
    }

    public boolean isClosed() {
        return this.state == CLOSED;
    }

    public boolean isClosing() {
        return this.state == CLOSING;
    }
}
