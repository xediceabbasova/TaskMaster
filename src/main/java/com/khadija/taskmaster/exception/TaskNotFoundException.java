package com.khadija.taskmaster.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super(String.format("Task with ID %d not found", id));
    }
}
