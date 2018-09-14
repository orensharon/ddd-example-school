package com.sharono.example.ddd.school;

public interface School {
    double MIN_STUDENT_AGE = 5;

    interface ClassRoomState {
        int CLOSED = 0;
        int OPENING = 1;
        int OPENED = 2;
        int CLOSING = 3;
    }

    interface StudentState {
        int CHECKED_OUT = 0;
        int CHECKING_OUT = 0;
        int CHECKING_IN = 0;
        int CHECKED_IN = 1;
        int EXEMPTED = 2;
        int MISSING = 3;
    }
}
