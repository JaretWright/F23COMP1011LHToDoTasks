package com.example.f23comp1011lhtodotasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person fred;
    @BeforeEach
    void setUp() {
        fred = new Person("Fred Flintstone", "fred@rocks.com");
    }

    @Test
    void isValidPostalCode() {
        assertTrue(fred.isValidPostalCode("L1V 3B7"));
    }

    @Test
    void isInvalidPostalCode() {
        assertFalse(fred.isValidPostalCode("4E5 3B7"));
    }

    @Test
    void isInvalidPostalCodeNoSpaces() {
       assertFalse(fred.isValidPostalCode("L1V3B7"));
    }
}