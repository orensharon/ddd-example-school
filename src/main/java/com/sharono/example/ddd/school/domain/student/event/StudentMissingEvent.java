package com.sharono.example.ddd.school.domain.student.event;

import com.sharono.example.ddd.school.util.eda.framework.AbstractEvent;

public class StudentMissingEvent extends AbstractEvent {

    private final int studentId;
    private final int classRoomId;

    public StudentMissingEvent(int studentId, int classRoomId) {
        this.studentId = studentId;
        this.classRoomId = classRoomId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getClassRoomId() {
        return classRoomId;
    }
}
