package com.sharono.example.ddd.school.application;

import com.sharono.example.ddd.school.domain.class_room.ClassRoom;
import com.sharono.example.ddd.school.util.eda.framework.Event;
import com.sharono.example.ddd.school.util.eda.framework.EventDispatcher;
import rx.Completable;
import rx.schedulers.Schedulers;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClassRoomService {

    private final EventDispatcher dispatcher;

    private final List<ClassRoom> classRooms;

    public ClassRoomService(EventDispatcher dispatcher, List<ClassRoom> classRooms) {
        if (classRooms == null || dispatcher == null) {
            throw new NullPointerException();
        }
        this.classRooms = classRooms;
        this.dispatcher = dispatcher;
    }

    public Completable open(int classRoomId) {
        ClassRoom classRoom = this.classRooms.get(classRoomId);
        if (classRoom.isClosing() || classRoom.isOpened()) {
            return Completable.complete();
        }
        if (classRoom.isClosed()) {
            classRoom.open();
            this.dispatch(classRoom);
        }
        return Completable
                .fromObservable(rx.Observable.timer(2, TimeUnit.SECONDS))
                .subscribeOn(Schedulers.io());
    }

    public Completable close(int classRoomId) {
        ClassRoom classRoom = this.classRooms.get(classRoomId);
        if (classRoom.isOpening() || classRoom.isClosed()) {
            return Completable.complete();
        }
        if (classRoom.isOpened()) {
            classRoom.close();
            this.dispatch(classRoom);
        }
        return Completable
                .fromObservable(rx.Observable.timer(2, TimeUnit.SECONDS))
                .subscribeOn(Schedulers.io());
    }

    public void opened(int classRoomId) {
        ClassRoom classRoom = this.classRooms.get(classRoomId);
        classRoom.opened();
        this.dispatch(classRoom);
    }

    public void closed(int classRoomId) {
        ClassRoom classRoom = this.classRooms.get(classRoomId);
        classRoom.closed();
        this.dispatch(classRoom);
    }

    private void dispatch(ClassRoom classRoom) {
        for (Event event : classRoom.dequeue()) {
            this.dispatcher.dispatch(event);
        }
    }
}
