package com.example.Springboot.demo.error;

import com.example.Springboot.demo.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorMessage> departmentNotFoundException(DepartmentNotFoundException exception, WebRequest webRequest) {

        ErrorMessage message = null;
        if (exception.getMessage().equals("Department Not Found to update")) {
            message = new ErrorMessage(HttpStatus.NOT_IMPLEMENTED, exception.getMessage());
        }

        if (exception.getMessage().equals("Department Not Available")) {
            message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        }


        return ResponseEntity.status(message.getStatus()).body(message);
    }
}
