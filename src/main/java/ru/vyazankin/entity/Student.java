package ru.vyazankin.entity;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mark")
    private Integer mark;

    public Student() {
    }

    public Student(String name, Integer mark) {
        this.name = name;
        this.mark = mark;
    }

    public Student(Long id, String name, Integer mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }
}
