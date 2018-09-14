package com.sharono.example.ddd.school;

public interface School {
    double MIN_STUDENT_AGE = 5;

    interface ClassRoomState {
        int OPENED = 0;
        int CLOSED = 1;
        String[] N = {"Opened", "Closed"};
    }

    interface StudentState {
        int UNKNOWN = 0;
        int CHECKED_OUT = 1;
        int CHECKED_IN = 1;
        int EXEMPTED = 2;
        String[] N = {"Unknown", "Checked-Out", "Checked-In", "Exempted"};
    }
}
