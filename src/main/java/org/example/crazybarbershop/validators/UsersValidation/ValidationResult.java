package org.example.crazybarbershop.validators.UsersValidation;

import lombok.Getter;
import org.example.crazybarbershop.util.Error;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    @Getter
    private final List<Error> errors = new ArrayList<>();

    public void add(Error error) {

        this.errors.add(error);

    }

    public boolean isValid() {

        return errors.isEmpty();

    }

}
