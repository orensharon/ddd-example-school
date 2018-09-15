package com.sharono.example.ddd.school.util.mock;

import com.sharono.example.ddd.school.domain.class_room.ClassRoom;
import com.sharono.example.ddd.school.domain.school.School;
import com.sharono.example.ddd.school.domain.student.Student;
import com.sharono.example.ddd.school.domain.teacher.Teacher;

import java.util.ArrayList;
import java.util.List;

import static com.sharono.example.ddd.school.School.SchoolState.CLOSED;
import static com.sharono.example.ddd.school.School.StudentState.CHECKED_OUT;

public class LocalCacheMock {

    public static School mockSchool(int id) {
        List<ClassRoom> classRooms = new ArrayList<>();
        classRooms.add(mockClassRoom(0, 5));
        return new School(id, classRooms, CLOSED);
    }

    private static ClassRoom mockClassRoom(int id, int size) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            students.add(mockStudent(i));
        }
        Teacher teacher = new Teacher(id, "teacher" + id, "phone" + id, "pictureUrl");
        return new ClassRoom(id, students, teacher);
    }

    private static Student mockStudent(int id) {
        return new Student(id, "name" + id, 6, "phone-" + id, "pictureUrl", CHECKED_OUT);
    }

}
