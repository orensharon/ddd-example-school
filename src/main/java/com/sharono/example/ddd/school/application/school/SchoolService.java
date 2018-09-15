package com.sharono.example.ddd.school.application.school;

import com.sharono.example.ddd.school.application.IGateway;
import com.sharono.example.ddd.school.application.IRepository;
import com.sharono.example.ddd.school.application.school.request.*;
import com.sharono.example.ddd.school.domain.Aggregate;
import com.sharono.example.ddd.school.domain.class_room.ClassRoom;
import com.sharono.example.ddd.school.domain.school.School;
import com.sharono.example.ddd.school.domain.student.Student;
import com.sharono.example.ddd.school.domain.teacher.Teacher;
import com.sharono.example.ddd.school.util.eda.framework.Event;
import com.sharono.example.ddd.school.util.eda.framework.EventDispatcher;
import io.reactivex.Completable;
import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;


public class SchoolService {

    private final EventDispatcher dispatcher;

    private final SchoolGateway gateway;
    private final SchoolRepository repository;

    private School school;

    public SchoolService(EventDispatcher dispatcher, IGateway gateway, IRepository repository) {
        if (dispatcher == null || gateway == null || repository == null) {
            throw new NullPointerException();
        }
        this.dispatcher = dispatcher;
        this.gateway = (SchoolGateway) gateway;
        this.repository = (SchoolRepository) repository;
    }

    public Observable<SchoolResponse> getSchool(int schoolId) {
        // Read from local cache
        this.school = this.repository.load(schoolId);
        return this.gateway.getSchool(new SchoolRequest(schoolId));
    }

    public Completable open() {
        if (this.school.isClosing() || this.school.isOpened()) {
            return Completable.complete();
        }
        if (this.school.isClosed()) {
            this.school.open();
            this.dispatch(this.school);
            this.repository.store(this.school);
        }
        return this.gateway.open(new OpenRequest(this.school.getId()));
    }

    public Completable close() {
        if (this.school.isOpening() || this.school.isClosed()) {
            return Completable.complete();
        }
        if (this.school.isOpened()) {
            this.school.close();
            this.dispatch(this.school);
            this.repository.store(this.school);
        }
        return this.gateway.close(new CloseRequest(school.getId()));
    }

    public void gotSchool(SchoolResponse response) {
        // update school
        this.school = this.updateSchool(response);
        this.repository.store(this.school);
    }

    public void opened() {
        this.school.opened();
        this.dispatch(this.school);
        this.repository.store(this.school);
    }

    public void closed() {
        this.school.closed();
        this.dispatch(this.school);
        this.repository.store(this.school);
    }

    private void dispatch(Aggregate aggregate) {
        for (Event event : aggregate.dequeue()) {
            this.dispatcher.dispatch(event);
        }
    }

    private School updateSchool(SchoolResponse response) {
        List<ClassRoom> classRooms = new ArrayList<>();
        for (SchoolResponse.Class classRoom : response.classes) {
            List<Student> students = new ArrayList<>();
            for (SchoolResponse.Student student : classRoom.students) {
                students.add(new Student(student.id, student.name, student.age, student.phoneNumber, student.pictureUrl, student.state));
            }
            Teacher teacher = new Teacher(
                    classRoom.teacher.id,
                    classRoom.teacher.name,
                    classRoom.teacher.phoneNumber,
                    classRoom.teacher.pictureUrl
            );
            classRooms.add(new ClassRoom(classRoom.id, students, teacher));
        }
        return new School(response.id, classRooms, response.state);
    }
}
