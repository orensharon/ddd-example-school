package com.sharono.example.ddd.school.domain.student;

import com.sharono.example.ddd.school.School;
import com.sharono.example.ddd.school.domain.Aggregate;
import com.sharono.example.ddd.school.domain.student.event.CheckedInEvent;
import com.sharono.example.ddd.school.domain.student.event.CheckedOutEvent;
import com.sharono.example.ddd.school.domain.student.event.CheckingInEvent;
import com.sharono.example.ddd.school.domain.student.event.CheckingOutEvent;

import static com.sharono.example.ddd.school.School.StudentState.*;

public class Student extends Aggregate {

    private final int id;
    private final String name;
    private final double age;
    private final String phoneNumber;
    private final String picture;

    private int state;

    public Student(int id ,String name, double age, String phoneNumber, String picture, int state) {
        if (name == null || phoneNumber == null || picture == null) {
            throw new NullPointerException();
        }
        if (age < School.MIN_STUDENT_AGE) {
            throw new IllegalArgumentException(String.format("Illegal age: %.1f", age));
        }
        if (name.trim().equals("")) {
            throw new IllegalArgumentException(String.format("Illegal student name: %s", name));
        }
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
        this.state = state;
    }

    public void checkIn() {
        if (this.state != CHECKED_OUT) {
            throw new NullPointerException("Illegal action");
        }
        this.state = CHECKING_IN;
        this.enqueue(new CheckingInEvent(this.id));
    }

    public void checkOut() {
        if (this.state != CHECKED_IN) {
            throw new RuntimeException("Illegal action");
        }
        this.state = CHECKING_OUT;
        this.enqueue(new CheckingOutEvent(this.id));
    }

    public void checkedIn() {
        if (this.state != CHECKED_IN) {
            this.state = CHECKED_IN;
            this.enqueue(new CheckedInEvent(this.id));
        }
    }

    public void checkedOut() {
        if (this.state != CHECKED_OUT) {
            this.state = CHECKED_OUT;
            this.enqueue(new CheckedOutEvent(this.id));
        }
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
