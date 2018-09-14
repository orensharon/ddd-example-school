package com.sharono.example.ddd.school.domain;

import com.sharono.example.ddd.school.Constants;

public class Student {

    private final String name;
    private final double age;
    private final String phoneNumber;
    private final String picture;

    public Student(String name, double age, String phoneNumber, String picture) {
        if (name == null || phoneNumber == null || picture == null) {
            throw new NullPointerException();
        }
        if (age < Constants.MIN_STUDENT_AGE) {
            throw new IllegalArgumentException(String.format("Illegal age: %.1f", age));
        }
        if (name.trim().equals("")) {
            throw new IllegalArgumentException(String.format("Illegal student name: %s", name));
        }
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
