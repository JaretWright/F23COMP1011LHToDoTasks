package com.example.f23comp1011lhtodotasks;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.TreeSet;

public class Task {

    private int taskID;
    private String title, description, category;
    private Person assignedTo;
    private LocalDate creationDate, dueDate;
    private int priority;
    private Status status;

    /**
     * To be used for a new task
     * @param title
     * @param description
     * @param category
     * @param assignedTo
     * @param dueDate
     * @param priority
     */
    public Task(String title, String description, String category, Person assignedTo, LocalDate dueDate, int priority) {
        creationDate = LocalDate.now();
        status = Status.CREATED;
        setTitle(title);
        setDescription(description);
        setCategory(category);
        setAssignedTo(assignedTo);
        setDueDate(dueDate);
        setPriority(priority);
    }

    /**
     * This constructor should be used when retreiving a Task object from the database
     */
    public Task(int taskID, String title, String description, String category, Person assignedTo,
                LocalDate creationDate, LocalDate dueDate, int priority, Status status) {
        if (taskID >0)
            this.taskID = taskID;
        else
            throw new IllegalArgumentException("taskID should be greater than 0");

        setTitle(title);
        setDescription(description);
        this.category = category;  //accepting the input without calling the set method for performance reasons
        setAssignedTo(assignedTo);
        setCreationDate(creationDate);
        setDueDate(dueDate);
        setPriority(priority);
        setStatus(status);
    }

    /**
     * An existing task
     * @param title
     * @param description
     * @param category
     * @param assignedTo
     * @param creationDate
     * @param dueDate
     * @param priority
     */
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

    public int getTaskID() {
        return taskID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (lengthValidator(title, 70))
            this.title = title;
        else
            throw new IllegalArgumentException("title must be 1 to 70 characters");
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
     * Tree -> this will automatically sort the objects
     * @return
     */
    public void setCategory(String category) {
        category = category.trim().toLowerCase();
        ArrayList<String> categories = DBUtility.getCategories();
        if (categories.contains(category))
            this.category = category;
        else
            throw new IllegalArgumentException("category must be one of "+categories);
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
        if ((creationDate.isBefore(LocalDate.now()) || !dueDate.isBefore(LocalDate.now())
         && (creationDate.isBefore(dueDate) || creationDate.isEqual(dueDate))))
        {
                this.dueDate = dueDate;
        }
        else
            throw new IllegalArgumentException("due date cannot be before today");
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority>=1 && priority<=3)
            this.priority = priority;
        else
            throw new IllegalArgumentException("Priority must be in the range of 1-3");
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String toString()
    {
        return String.format("%s assigned to %s",title,assignedTo);
    }

    public long getDaysUntilDue()
    {
        return ChronoUnit.DAYS.between(LocalDate.now(),dueDate);
    }

    /**
     * This method will search the taskID, title and category for the searchTerm
     */
    public boolean contains(String searchTerm, boolean priority1, boolean priority2, boolean priority3)
    {
            searchTerm = searchTerm.toLowerCase();
            return (title.toLowerCase().contains(searchTerm) ||
                    category.toLowerCase().contains(searchTerm) ||
                    Integer.toString(taskID).contains(searchTerm)) &&
                    (priority1 && priority==1 || priority2 && priority ==2 ||
                            priority3 && priority==3);
    }
}

