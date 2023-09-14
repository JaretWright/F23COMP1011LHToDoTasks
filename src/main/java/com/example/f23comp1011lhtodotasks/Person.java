package com.example.f23comp1011lhtodotasks;

public class Person {
    private String fullName, email;

    public Person(String fullName, String email) {
        setFullName(fullName);
        setEmail(email);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        fullName = fullName.trim();
        if (fullName.matches("[A-z\\s\\-]*"))
            this.fullName = fullName;
        else
            throw new IllegalArgumentException("name can only contain letters, space or -");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email.trim();
        if (email.matches("^(.+)@(\\S+)$"))
            this.email = email;
        else
            throw new IllegalArgumentException("enter a valid email address");
    }
}
