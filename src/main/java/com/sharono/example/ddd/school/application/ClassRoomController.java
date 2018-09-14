package com.sharono.example.ddd.school.application;

import com.sharono.example.ddd.school.domain.ClassRoom;
import rx.Completable;
import rx.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClassRoomController {

    private final List<ClassRoom> classRooms;

    public ClassRoomController(List<ClassRoom> classRooms) {
        if (classRooms == null) {
            throw new NullPointerException();
        }
        this.classRooms = classRooms;
    }

    public void open(int classRoomId) {
        ClassRoom classRoom = this.classRooms.get(classRoomId);
        if (classRoom.isClosing() || classRoom.isOpened()) {
            return;
        }
        if (classRoom.isClosed()) {
            classRoom.open();
        }
        Completable
                .fromObservable(Observable.timer(2, TimeUnit.SECONDS))
                .subscribe();
    }

    public void close(int classRoomId) {
        ClassRoom classRoom = this.classRooms.get(classRoomId);
        if (classRoom.isOpening() || classRoom.isClosed()) {
            return;
        }
        if (classRoom.isOpened()) {
            classRoom.close();
        }
        Completable
                .fromObservable(Observable.timer(2, TimeUnit.SECONDS))
                .subscribe();
    }
}
