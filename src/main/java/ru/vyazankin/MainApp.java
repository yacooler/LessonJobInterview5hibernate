package ru.vyazankin;

import org.flywaydb.core.Flyway;


public class MainApp {

    public static void main(String[] args) {
        flyAway();
        new StudentsTest().startTest();
    }

    private static void flyAway(){
        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:file:./target/students_base", "sa", null).load();
        flyway.migrate();
    }



}
