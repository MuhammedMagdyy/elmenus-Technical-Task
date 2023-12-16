package org.elmenus.drones.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({
            NotFoundException.class, BadRequestException.class
    })
    public ResponseEntity<ApiResponse<Void>> handleException(RuntimeException e) {
        HttpStatus status = statusCode(e);
        ApiResponse<Void> response = new ApiResponse<>(e.getMessage(), null);

        return new ResponseEntity<>(response, status);
    }

    private HttpStatus statusCode(RuntimeException e) {
        if (e instanceof NotFoundException) {
            return HttpStatus.NOT_FOUND;
        } else if (e instanceof BadRequestException) {
            return HttpStatus.BAD_REQUEST;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
