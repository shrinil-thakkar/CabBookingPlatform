package org.cabbooking.exception;

public class IncorrectTripStatusException extends Exception {
    public IncorrectTripStatusException(String message) {
        super(message);
    }

    public IncorrectTripStatusException() {
        super();
    }
}
