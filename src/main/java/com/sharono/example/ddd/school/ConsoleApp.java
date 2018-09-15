package com.sharono.example.ddd.school;

import com.sharono.example.ddd.school.application.App;

public class ConsoleApp {

    public static void main(String[] args) {
        App app = new App();
        app.init();
        app.start();
    }
}
