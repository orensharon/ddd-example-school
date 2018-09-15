package com.sharono.example.ddd.school.application;


import com.sharono.example.ddd.school.application.school.SchoolController;
import com.sharono.example.ddd.school.application.school.SchoolGateway;
import com.sharono.example.ddd.school.application.school.SchoolService;
import com.sharono.example.ddd.school.application.school.SchoolRepository;
import com.sharono.example.ddd.school.domain.school.School;
import com.sharono.example.ddd.school.util.eda.framework.EventDispatcher;
import com.sharono.example.ddd.school.util.mock.RetrofitFactory;

public class App {

    private final EventDispatcher dispatcher;

    private School school;
    private SchoolController schoolController;

    public App() {
        this.dispatcher = new EventDispatcher();
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) { }
        }).start();
    }

    public void init() {
        SchoolRepository schoolRepository = new SchoolRepository();
        SchoolGateway schoolGateway = new SchoolGateway(RetrofitFactory.getAdapter());
        SchoolService schoolService = new SchoolService(this.dispatcher, schoolGateway, schoolRepository);
        this.schoolController = new SchoolController(schoolService);
    }

    public void start() {
        this.getSchoolController().getSchool(0);
        /*if (this.school == null) {
            throw new RuntimeException();
        }*/
    }

    public SchoolController getSchoolController() {
        return schoolController;
    }
}