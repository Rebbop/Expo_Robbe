package be.vdab.expo.exceptions;

public class NoTicketsAvailableException extends RuntimeException {
    public NoTicketsAvailableException(String message) {
        super(message);
    }
}