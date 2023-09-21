package com.example.f23comp1011lhtodotasks;

import java.sql.SQLException;
import java.util.ArrayList;

public class TestingMain {
    public static void main(String[] args) {
        ArrayList<Person> users = DBUtility.getUsers();
        System.out.println("\n" + users);
    }
}
