package org.cabbooking.exception;

public class IncorrectDriverException extends Exception {
    public IncorrectDriverException(String message) {
        super(message);
    }

    public IncorrectDriverException() {
        super();
    }
}
