package com.sharono.example.ddd.school.domain.class_room.event;

import com.sharono.example.ddd.school.util.eda.framework.AbstractEvent;

public class ClassRoomClosedEvent extends AbstractEvent {

    private final int classRoomId;

    public ClassRoomClosedEvent(int classRoomId) {
        this.classRoomId = classRoomId;
    }

    public int getClassRoomId() {
        return classRoomId;
    }
}
