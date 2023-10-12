package com.example.f23comp1011lhtodotasks;

public class Person {
    private String fullName, email, postalCode;

    public Person(String fullName, String email) {
        setFullName(fullName);
        setEmail(email);
    }

    public Person(String fullName, String email, String postalCode) {
        setFullName(fullName);
        setEmail(email);
        setPostalCode(postalCode);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        fullName = fullName.trim();
        if (fullName.matches("[A-z\\s\\-]+"))
            this.fullName = fullName;
        else
            throw new IllegalArgumentException("name cannot be blank and use letters, space or -");
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


    @Override
    public String toString()
    {
        return fullName;
    }

    /**
     * Create a regex to ensure that the person has a valid pattern for their
     * postal code in Canada.
     *
     * Check to see if the postal code matches the pattern of
     * LNL NLN where L is a letter and N is a number
     *
     * The method should receive a String as an argument and return true if it matches
     * the pattern, false otherwise.
     */
    public boolean isValidPostalCode(String postalCode){
        postalCode = postalCode.toUpperCase();
        return postalCode.matches("[A-Z][0-9][A-Z]\\s?[0-9][A-Z][0-9]");
    }

    public String getPostalCode() {
        return postalCode.substring(0,3)+" "+postalCode.substring(3);
    }

    public void setPostalCode(String postalCode) {
        postalCode = postalCode.trim().replaceAll("\\s+","").toUpperCase();
        if (isValidPostalCode(postalCode))
            this.postalCode = postalCode;
        else
            throw new IllegalArgumentException("Postal code must be in the format LNL NLN where L is a letter and N is a number");

    }
}
