package hu.nye.rft.neptun.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to represent when user already exists.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Username already in use.")
public class UserNameAlreadyInUseException extends Exception {
    public UserNameAlreadyInUseException(String message) {
        super(message);
    }
}
