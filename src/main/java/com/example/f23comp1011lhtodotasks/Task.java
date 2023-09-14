package com.example.f23comp1011lhtodotasks;

import java.time.LocalDate;

public class Task {
    private String title, description, category;
    private Person assignedTo;
    private LocalDate dueDate;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public void setDueDate(LocalDate dueDate) {
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
