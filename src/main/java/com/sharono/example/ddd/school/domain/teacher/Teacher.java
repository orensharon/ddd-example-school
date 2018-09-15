package com.sharono.example.ddd.school.domain.teacher;

public class Teacher {

    private final int id;
    private final String name;
    private final String phoneNumber;
    private final String picture;

    public Teacher(int id, String name, String phoneNumber, String picture) {
        if (name == null || phoneNumber == null || picture == null) {
            throw new NullPointerException();
        }
        if (name.trim().equals("")) {
            throw new IllegalArgumentException(String.format("Illegal teacher name: %s", name));
        }
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
    }

    public int getId() {
        return id;
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
