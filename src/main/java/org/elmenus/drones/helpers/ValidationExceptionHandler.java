package org.elmenus.drones.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleInvalidArgument(MethodArgumentNotValidException exception) {
        List<Map<String, String>> errorsList = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            Map<String, String> error = new HashMap<>();
            error.put("field", fieldError.getField());
            error.put("message", fieldError.getDefaultMessage());
            errorsList.add(error);
        });

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Validation Error");
        response.put("errors", errorsList);

        return response;
    }
}
