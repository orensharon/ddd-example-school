package com.sharono.example.ddd.school.domain.school;

import com.sharono.example.ddd.school.domain.Aggregate;
import com.sharono.example.ddd.school.domain.class_room.ClassRoom;
import com.sharono.example.ddd.school.domain.school.event.SchoolClosedEvent;
import com.sharono.example.ddd.school.domain.school.event.SchoolClosingEvent;
import com.sharono.example.ddd.school.domain.school.event.SchoolOpenedEvent;
import com.sharono.example.ddd.school.domain.school.event.SchoolOpeningEvent;

import java.util.List;

import static com.sharono.example.ddd.school.School.SchoolState.*;

public class School extends Aggregate {

    private final int id;
    private final List<ClassRoom> classRooms;

    private int state;


    public School(int id, List<ClassRoom> classRooms, int state) {
        if (classRooms == null) {
            throw new NullPointerException();
        }
        this.id = id;
        this.classRooms = classRooms;
        this.state = state;
    }

    public void open() {
        if (!this.isClosed()) {
            throw new RuntimeException("Illegal action");
        }
        this.state = OPENING;
        this.enqueue(new SchoolOpeningEvent(this.id));
    }

    public void close() {
        if (!this.isOpened()) {
            throw new RuntimeException("Illegal action");
        }
        this.state = CLOSING;
        this.enqueue(new SchoolClosingEvent(this.id));
    }

    public void opened() {
        if (this.isOpening()) {
            this.state = OPENED;
            this.enqueue(new SchoolOpenedEvent(this.id));
        }
    }

    public void closed() {
        if (this.isClosing()) {
            this.state = CLOSED;
            this.enqueue(new SchoolClosedEvent(this.id));
        }
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

    public List<ClassRoom> getClassRooms() {
        return classRooms;
    }

    public int getId() {
        return id;
    }

    public int getState() {
        return state;
    }
}
