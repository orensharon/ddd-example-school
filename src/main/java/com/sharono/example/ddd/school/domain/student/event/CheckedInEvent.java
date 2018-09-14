package com.sharono.example.ddd.school.domain.student.event;

import com.sharono.example.ddd.school.util.eda.framework.AbstractEvent;

public class CheckedInEvent extends AbstractEvent {

    private final int studentId;

    public CheckedInEvent(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }
}
