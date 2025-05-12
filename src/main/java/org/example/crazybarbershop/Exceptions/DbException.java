package org.example.crazybarbershop.Exceptions;

public class DbException extends RuntimeException {

    public DbException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbException(String message) {
        super(message);
    }
}
