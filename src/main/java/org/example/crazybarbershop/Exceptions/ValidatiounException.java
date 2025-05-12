package org.example.crazybarbershop.Exceptions;

import lombok.Getter;
import org.example.crazybarbershop.util.Error;

import java.util.List;

public class ValidatiounException extends RuntimeException{

    @Getter
    private final List<Error> errors;

    public ValidatiounException(List<Error> errors) {
        this.errors = errors;
    }
}
