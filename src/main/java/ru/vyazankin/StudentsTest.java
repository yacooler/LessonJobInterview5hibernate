package ru.vyazankin;

import lombok.extern.log4j.Log4j;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyazankin.entity.Student;
import ru.vyazankin.repository.BaseRepo;
import ru.vyazankin.repository.StudentRepoImpl;
import ru.vyazankin.utils.HibernateSessionFactory;

import java.util.List;

public class StudentsTest {
    private final BaseRepo<Student, Long> studentRepo = new StudentRepoImpl();

    public void startTest(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        studentRepo.setSession(session);


        addStudents(session);
        printCount();
        deleteAlmostAll();
        printCount();
        renameFirst();
        printAll();

        session.getTransaction().commit();

        session.close();
        HibernateSessionFactory.shutdown();
    }

    private void addStudents(Session session){
        System.out.println("Adding students");
        for (int i = 0; i < 1000; i++) {
            studentRepo.save( new Student("Student" + i, (int) (Math.random() * 10)) );
        }
    }

    private void printCount(){
        System.out.println("Records:" + studentRepo.countAll());
    }

    private void deleteAlmostAll(){
        Long count = studentRepo.countAll();
        long l;
        Student student;
        List<Student> studentList = studentRepo.findAll();
        for (int i = 0; i < count - 10; i++) {
            student = studentList.get(i);
            System.out.println("Student" + studentList.get(i) + " will be deleted");
            studentRepo.deleteById(student.getId());
        }
        System.out.println("Students remain:");
        printAll();
    }

    private void renameFirst(){
        System.out.println("Rename student");
        if (studentRepo.countAll() <= 0){
            System.out.println("No students allowed");
            return;
        }
        Student student = studentRepo.findAll().get(0);
        student.setName("Вязанкин Павел");
        student.setMark(9);
        studentRepo.update(student);
    }

    private void printAll(){
        System.out.println("Students list from DB");
        studentRepo.findAll().forEach(System.out::println);
    }
}
