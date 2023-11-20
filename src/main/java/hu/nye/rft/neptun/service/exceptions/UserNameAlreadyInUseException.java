package hu.nye.rft.neptun.service.exceptions;

public class UserNameAlreadyInUseException extends Exception {
    public UserNameAlreadyInUseException(String message) {
        super(message);
    }
}
