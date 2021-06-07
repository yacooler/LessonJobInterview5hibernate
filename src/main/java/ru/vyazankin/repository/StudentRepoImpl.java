package ru.vyazankin.repository;

import org.hibernate.Session;
import ru.vyazankin.entity.Student;

import java.util.List;

public class StudentRepoImpl implements StudentRepo {

    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Student findByID(Long id) {
        return session.get(Student.class, id);
    }

    @Override
    public Student update(Student entity) {
        return (Student) session.merge(entity);
    }

    @Override
    public Student save(Student entity) {
        session.persist(entity);
        return (Student) entity;
    }

    @Override
    public List<Student> findAll() {
        return (List<Student>) session.createQuery("s from Student s").list();
    }

    @Override
    public void deleteById(Long id) {
        session.createQuery("delete from Student s where s.id = :id").setLong("id", id).executeUpdate();
    }
}
