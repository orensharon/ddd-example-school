package com.sharono.example.ddd.school.domain;

public class Teacher {

    private final String name;
    private final String phoneNumber;
    private final String picture;

    public Teacher(String name, String phoneNumber, String picture) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPicture() {
        return picture;
    }
}
