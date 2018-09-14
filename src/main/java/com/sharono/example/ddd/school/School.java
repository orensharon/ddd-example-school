package com.sharono.example.ddd.school;

public interface School {
    double MIN_STUDENT_AGE = 5;

    interface ClassRoomState {
        int CLOSED = 0;
        int OPENED = 1;
        String[] N = {"Closed", "Opened"};
    }

    interface StudentState {
        int CHECKED_OUT = 0;
        int CHECKED_IN = 1;
        int EXEMPTED = 2;
        int MISSING = 3;
        String[] N = {"Unknown", "Checked-Out", "Checked-In", "Exempted", "Idle"};
    }
}
