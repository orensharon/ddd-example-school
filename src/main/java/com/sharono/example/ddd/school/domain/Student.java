package com.sharono.example.ddd.school.domain;

import com.sharono.example.ddd.school.School;

import static com.sharono.example.ddd.school.School.StudentState.*;

public class Student {

    private final String name;
    private final double age;
    private final String phoneNumber;
    private final String picture;

    private int state;

    public Student(String name, double age, String phoneNumber, String picture, int state) {
        if (name == null || phoneNumber == null || picture == null) {
            throw new NullPointerException();
        }
        if (age < School.MIN_STUDENT_AGE) {
            throw new IllegalArgumentException(String.format("Illegal age: %.1f", age));
        }
        if (name.trim().equals("")) {
            throw new IllegalArgumentException(String.format("Illegal student name: %s", name));
        }
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
        this.state = state;
    }

    public void checkIn() {
        if (this.state == CHECKED_IN) {
            throw new RuntimeException("Illegal action. student already checked in");
        }
        int lastState = this.state;
        this.state = CHECKED_IN;
        // TODO: raise event - student checked in
        if (lastState == EXEMPTED) {
            // TODO: raise event, check in but exemption was declared
        }
    }

    public void checkOut() {
        if (this.state == CHECKED_OUT) {
            throw new RuntimeException("Illegal action. student already checked out");
        }
        if (this.state == EXEMPTED) {
            throw new RuntimeException("Illegal action. student exempted");
        }
        this.state = CHECKED_OUT;
        // TODO: raise event - student checked out
    }

    public void exempt() {
        if (this.state == EXEMPTED) {
            throw new RuntimeException("Illegal action. student already exempted");
        }
        if (this.state == CHECKED_IN) {
            throw new RuntimeException("Illegal action. student exempted");
        }
        this.state = EXEMPTED;
        // TODO: raise event - student exempted
    }

    public void missing() {
        if (this.state == MISSING) {
            throw new RuntimeException("Illegal action. student already missing");
        }
        if (this.state == EXEMPTED) {
            throw new RuntimeException("Illegal action. student exempted");
        }
        if (this.state == CHECKED_OUT) {
            throw new RuntimeException("Illegal action. student checked out");
        }
        this.state = MISSING;
        // TODO: raise event - student missing
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

    public int getState() {
        return state;
    }
}
