package com.services.order_ms.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    private List<FieldMessage> errors = new ArrayList<>();

    public void addError(String field, String message){
        errors.add(new FieldMessage(field, message));
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }
}
