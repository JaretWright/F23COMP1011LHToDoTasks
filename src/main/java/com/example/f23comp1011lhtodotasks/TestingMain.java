package com.example.f23comp1011lhtodotasks;

import java.sql.SQLException;

public class TestingMain {
    public static void main(String[] args) {
        Person person = new Person("Jaret Wright","jwrigh10@lakeheadu.ca");

        try {
            System.out.println(DBUtility.saveUserToDB(person));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
