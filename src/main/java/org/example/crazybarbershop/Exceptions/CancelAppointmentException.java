package org.example.crazybarbershop.Exceptions;

public class CancelAppointmentException extends RuntimeException{

    public CancelAppointmentException(String message) {
        super(message);
    }

    public CancelAppointmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
