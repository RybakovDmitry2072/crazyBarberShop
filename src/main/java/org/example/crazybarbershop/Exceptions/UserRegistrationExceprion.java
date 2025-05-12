package org.example.crazybarbershop.Exceptions;

public class UserRegistrationExceprion extends RuntimeException{

    public UserRegistrationExceprion(String message) {
        super(message);
    }

    public UserRegistrationExceprion(String message, Throwable cause) {
        super(message, cause);
    }
}
