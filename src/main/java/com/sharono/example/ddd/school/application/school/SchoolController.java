package com.sharono.example.ddd.school.application.school;

import com.sharono.example.ddd.school.domain.school.School;

public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService service) {
        if (service == null) {
            throw new NullPointerException();
        }
        this.schoolService = service;
    }

    public void getSchool(int schoolId) {
        this.schoolService.getSchool(schoolId)
                .subscribe(response -> this.schoolService.gotSchool(response));
    }

    public void open() {
        this.schoolService.open()
                .doOnComplete(this.schoolService::opened)
                .subscribe();
    }

    public void close() {
        this.schoolService.close()
                .doOnComplete(this.schoolService::closed)
                .subscribe();
    }
}
