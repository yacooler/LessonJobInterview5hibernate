package ru.vyazankin;

import org.flywaydb.core.Flyway;
import ru.vyazankin.entity.Student;

public class MainApp {

    public static void main(String[] args) {
        flyAway();
    }

    private static void flyAway(){
        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:file:./target/students_base", "sa", null).load();
        flyway.migrate();
    }

    private static void addStudents(){
        for (int i = 0; i < 1000; i++) {
            Student student = new Student("Student" + i, (int) Math.floor(Math.random() * 10F));

        }
    }

}
