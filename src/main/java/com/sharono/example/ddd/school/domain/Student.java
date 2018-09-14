package com.sharono.example.ddd.school.domain;

public class Student {

    private final String name;
    private final double age;
    private final String phoneNumber;
    private final String picture;

    public Student(String name, double age, String phoneNumber, String picture) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public double getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPicture() {
        return picture;
    }
}
