package com.sharono.example.ddd.school.application;

import rx.schedulers.Schedulers;
import java.util.concurrent.Executor;

public class ClassRoomController {

    private final Executor executor;

    private final ClassRoomService classRoomService;

    public ClassRoomController(Executor executor, ClassRoomService service) {
        if (executor == null || service == null) {
            throw new NullPointerException();
        }
        this.executor = executor;
        this.classRoomService = service;
    }

    public void open(int classRoomId) {
        this.classRoomService.open(classRoomId)
                .observeOn(Schedulers.from(this.executor))
                .doOnCompleted(() -> this.classRoomService.opened(classRoomId))
                .subscribe();
    }

    public void close(int classRoomId) {
        this.classRoomService.close(classRoomId)
                .observeOn(Schedulers.from(this.executor))
                .doOnCompleted(() -> this.classRoomService.closed(classRoomId))
                .subscribe();
    }
}
