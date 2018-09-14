package com.sharono.example.ddd.school.domain.class_room.event;

import com.sharono.example.ddd.school.util.eda.framework.AbstractEvent;

public class ClassRoomOpeningEvent extends AbstractEvent {

    private final int classRoomId;

    public ClassRoomOpeningEvent(int classRoomId) {
        this.classRoomId = classRoomId;
    }

    public int getClassRoomId() {
        return classRoomId;
    }
}
