package com.sharono.example.ddd.school.domain.school.event;

import com.sharono.example.ddd.school.util.eda.framework.AbstractEvent;

public class SchoolClosingEvent extends AbstractEvent {

    private final int classRoomId;

    public SchoolClosingEvent(int classRoomId) {
        this.classRoomId = classRoomId;
    }

    public int getClassRoomId() {
        return classRoomId;
    }
}
