package com.sharono.example.ddd.school.application.school;

import com.sharono.example.ddd.school.application.IRepository;
import com.sharono.example.ddd.school.domain.class_room.ClassRoom;
import com.sharono.example.ddd.school.domain.school.School;
import com.sharono.example.ddd.school.domain.student.Student;
import com.sharono.example.ddd.school.domain.teacher.Teacher;
import com.sharono.example.ddd.school.util.mock.LocalCacheMock;

import java.util.ArrayList;
import java.util.List;

public class SchoolRepository implements IRepository<School> {

    private School school;

    @Override
    public void store(School item) {
        List<ClassRoom> classRooms = new ArrayList<>();
        for (ClassRoom classRoom : item.getClassRooms()) {
            List<Student> students = new ArrayList<>();
            for (Student student : classRoom.getStudents()) {
                students.add(
                        new Student(student.getId(), student.getName(), student.getAge(), student.getPhoneNumber(), student.getPicture(), student.getState())
                );
            }
            Teacher teacher = new Teacher(
                    classRoom.getTeacher().getId(),
                    classRoom.getTeacher().getName(),
                    classRoom.getTeacher().getPhoneNumber(),
                    classRoom.getTeacher().getPicture()
            );
            classRooms.add(new ClassRoom(classRoom.getId(), students, teacher));
        }
        int id = item.getId();
        int state = item.getState();
        this.school = new School(id, classRooms, state);
    }

    @Override
    public School load(int id) {
        if (this.school == null) {
            this.store(LocalCacheMock.mockSchool(id));
        }
        return school;
    }
}
