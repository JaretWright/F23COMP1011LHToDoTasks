package com.example.f23comp1011lhtodotasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeSet;

public class Task {
    private String title, description, category;
    private Person assignedTo;
    private LocalDate creationDate, dueDate;
    private int priority;
    private boolean inProgress, done;

    public Task(String title, String description, String category, Person assignedTo, LocalDate dueDate, int priority) {
        setTitle(title);
        setDescription(description);
        setCategory(category);
        setAssignedTo(assignedTo);
        setDueDate(dueDate);
        setPriority(priority);
        inProgress = false;
        done = false;
    }

    public Task(String title, String description, String category, Person assignedTo, LocalDate creationDate, LocalDate dueDate, int priority) {
        this(title,description,category,assignedTo,dueDate,priority);  //calls the other constructor
        setCreationDate(creationDate);
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    private void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (lengthValidator(title, 30))
            this.title = title;
        else
            throw new IllegalArgumentException("title must be 1 to 30 characters");
    }

    /**
     * This method will ensure the string has 1 to X number of characters and remove
     * any leading or trailing white spaces
     * @return
     */
    public boolean lengthValidator(String textToValidate, int maxLengthOfString)
    {
        textToValidate = textToValidate.trim();
        return textToValidate.length()>=1 && textToValidate.length()<=maxLengthOfString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (lengthValidator(description, 250))
            this.description = description;
        else
            throw new IllegalArgumentException("description must be 1 to 250 characters");
    }

    public String getCategory() {
        return category;
    }

    /**
     * List -> handle multiple objects, user can order the elements, can support duplicates
     * Set -> this prevents duplicates, the system sets the order
     * @return
     */
    public static TreeSet<String> getCategories()
    {
        TreeSet<String> categories = new TreeSet<>();
        categories.add("personal");
        categories.add("work");
        categories.add("school");
        return categories;
    }

    public void setCategory(String category) {
        category = category.trim().toLowerCase();
        if (getCategories().contains(category))
            this.category = category;
        else
            throw new IllegalArgumentException("category must be one of "+getCategories());
    }

    public Person getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Person assignedTo) {
        this.assignedTo = assignedTo;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * new task -> due date can only be today or in the future
     * existing task -> due date can be anytime
     * @param dueDate
     */
    public void setDueDate(LocalDate dueDate) {
        //test if it is an existing task
        if (creationDate.isBefore(LocalDate.now()))
            this.dueDate = dueDate;
        else if (dueDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("due date cannot be before today");
        else
            this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
