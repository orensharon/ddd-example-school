package com.sharono.example.ddd.school.domain.student.event;

import com.sharono.example.ddd.school.util.eda.framework.AbstractEvent;

public class CheckingOutEvent extends AbstractEvent {

    private final int studentId;

    public CheckingOutEvent(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }
}
