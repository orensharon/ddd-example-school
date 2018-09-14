package com.sharono.example.ddd.school.domain.class_room;

import com.sharono.example.ddd.school.domain.Aggregate;
import com.sharono.example.ddd.school.domain.class_room.event.ClassRoomClosedEvent;
import com.sharono.example.ddd.school.domain.class_room.event.ClassRoomClosingEvent;
import com.sharono.example.ddd.school.domain.class_room.event.ClassRoomOpenedEvent;
import com.sharono.example.ddd.school.domain.class_room.event.ClassRoomOpeningEvent;
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
        if (!this.isClosed()) {
            throw new RuntimeException("Illegal action");
        }
        this.state = OPENING;
        this.enqueue(new ClassRoomOpeningEvent(this.id));
    }

    public void close() {
        if (!this.isOpened()) {
            throw new RuntimeException("Illegal action");
        }
        this.state = CLOSING;
        this.enqueue(new ClassRoomClosingEvent(this.id));
    }

    public void opened() {
        if (this.isOpening()) {
            this.state = OPENED;
            this.enqueue(new ClassRoomOpenedEvent(this.id));
        }
    }

    public void closed() {
        if (this.isClosing()) {
            this.state = CLOSED;
            this.enqueue(new ClassRoomClosedEvent(this.id));
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
