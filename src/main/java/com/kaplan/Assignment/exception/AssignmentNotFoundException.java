package com.kaplan.Assignment.exception;

public class AssignmentNotFoundException extends RuntimeException {
    private String message;
    public AssignmentNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public AssignmentNotFoundException() {
    }
}
