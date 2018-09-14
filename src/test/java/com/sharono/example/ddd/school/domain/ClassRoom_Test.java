package com.sharono.example.ddd.school.domain;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static com.sharono.example.ddd.school.School.ClassRoomState.CLOSED;
import static com.sharono.example.ddd.school.School.StudentState.CHECKED_OUT;

public class ClassRoom_Test {

    @Rule
    public ExpectedException exception = ExpectedException.none();


    private ClassRoom classRoom;
    private static List<Student> students = new ArrayList<Student>();

    @BeforeClass
    public static void setupStudents() {
        for (int i = 0; i < 10; i++) {
            String name = String.format("name-%d", i + 1);
            double age = 6;
            students.add(new Student(name, age, "phone", "picture", CHECKED_OUT));
        }
    }

    @Before
    public void setup() {
        classRoom = new ClassRoom(0, students, new Teacher("teacher", "phone", "picture"), CLOSED);
    }

    @Test
    public void open() {
        classRoom.open();
        Assert.assertTrue("Incorrect class state", classRoom.isOpened());
    }

    @Test
    public void openAndClose() {
        this.open();
        classRoom.close();
        Assert.assertTrue("Incorrect class state", classRoom.isClosed());
    }

    @Test
    public void closeAndClose() {
        this.open();
        classRoom.close();

        exception.expect(RuntimeException.class);
        exception.expectMessage("Illegal command - already closed");
        classRoom.close();
    }

    @Test
    public void openAndOpen() {
        this.open();
        exception.expect(RuntimeException.class);
        exception.expectMessage("Illegal command - already opened");
        this.open();
    }
}
