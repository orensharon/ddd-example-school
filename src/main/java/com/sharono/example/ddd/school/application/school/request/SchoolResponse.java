package com.sharono.example.ddd.school.application.school.request;

import java.util.List;

public class SchoolResponse {
    public int id;
    public List<Class> classes;
    public int state;

    public static class Class {
        public int id;
        public List<Student> students;
        public Teacher teacher;
    }

    public static class Student {
        public int id;
        public String name;
        public double age;
        public String pictureUrl;
        public String phoneNumber;
        public int state;
    }

    public static class Teacher {
        public int id;
        public String name;
        public String phoneNumber;
        public String pictureUrl;
    }
}
