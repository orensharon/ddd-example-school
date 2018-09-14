package com.sharono.example.ddd.school.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.sharono.example.ddd.school.School.StudentState.*;

public class Student_Test {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Student student;

    @Before
    public void setup() {
        student = new Student("name", 10.0d, "111-11-1111-11", "http://", CHECKED_OUT);
    }

    @Test
    public void checkIn() {
        student.checkIn();
        Assert.assertEquals("Incorrect student state", CHECKED_IN, student.getState());
    }

    @Test
    public void checkOut() {
        student.checkIn();
        student.checkOut();
        Assert.assertEquals("Incorrect student state", CHECKED_OUT, student.getState());
    }

    @Test
    public void exempt() {
        student.exempt();
        Assert.assertEquals("Incorrect student state", EXEMPTED, student.getState());
    }

    @Test
    public void missing() {
        student.missing();
        Assert.assertEquals("Incorrect student state", MISSING, student.getState());
    }

    @Test
    public void checkInAndCheckOut() {
        this.checkIn();
        student.checkOut();
        Assert.assertEquals("Incorrect student state", CHECKED_OUT, student.getState());
    }

    @Test
    public void checkInAndCheckIn() {
        this.checkIn();
        exception.expect(RuntimeException.class);
        exception.expectMessage("Illegal action: check-in. student already checked in");
        this.checkIn();
    }

    @Test
    public void checkInAndExempt() {
        this.checkIn();
        exception.expect(RuntimeException.class);
        exception.expectMessage("Illegal action: exempt. student checked-in");
        this.exempt();
    }

    @Test
    public void checkInAndMissing() {
        this.checkIn();
        exception.expect(RuntimeException.class);
        exception.expectMessage("Illegal action: missing. student checked-in");
        this.missing();
    }

    @Test
    public void exemptAndCheckIn() {
        this.exempt();
        exception.expect(RuntimeException.class);
        exception.expectMessage("Illegal action: check-in. student exempted");
        this.checkIn();
    }
}
