package com.sharono.example.ddd.school.domain.teacher;

public class Teacher {

    private final String name;
    private final String phoneNumber;
    private final String picture;

    public Teacher(String name, String phoneNumber, String picture) {
        if (name == null || phoneNumber == null || picture == null) {
            throw new NullPointerException();
        }
        if (name.trim().equals("")) {
            throw new IllegalArgumentException(String.format("Illegal teacher name: %s", name));
        }
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
