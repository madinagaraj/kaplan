package com.kaplan.Assignment.exception;

public class NoAssignmentWithTagFoundException extends RuntimeException {
    private String message;
    public NoAssignmentWithTagFoundException(String message) {
        super(message);
        this.message = message;
    }
    public NoAssignmentWithTagFoundException() {
    }
}
