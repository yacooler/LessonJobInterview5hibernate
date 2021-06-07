package ru.vyazankin;

import ru.vyazankin.entity.Student;
import ru.vyazankin.repository.BaseRepo;
import ru.vyazankin.repository.StudentRepoImpl;

public class StudentsTest {
    private final BaseRepo<Student, Long> studentRepo = new StudentRepoImpl();

    public void startTest(){
        System.out.println("Adding students");
        addStudents();
    }

    private void addStudents(){
        studentRepo.setSession(HiberSessionFactory.getSessionFactory().getCurrentSession());
        for (int i = 0; i < 1000; i++) {
            studentRepo.save( new Student("Student" + i, (int) (Math.random() * 10)) );
        }
    }
}
