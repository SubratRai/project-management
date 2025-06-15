package com.projectmanagement.project_management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


@RestControllerAdvice // it just act like catching net for rest api

public class GlobalExceptionHandler {


        // Validation Exception ( if missing name/email bring it to this method)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        //catch validation error and return
        // response entity used to return response status and body
        public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
            //this is a empty mapping where we store error message and field name
            Map<String, String> errors = new HashMap<>();
            //getFieldErrors bring all field validation
            ex.getBindingResult().getFieldErrors().forEach(err ->
                    //getFieldId() bring name error msg brig by getDefaultMessage
                    errors.put(err.getField(), err.getDefaultMessage())
            );
            //with 400 error show customize msg
            return ResponseEntity.badRequest().body(errors);
        }

        //Builtin Exception if we didn't get any data in service layer so give this exception
        @ExceptionHandler(NoSuchElementException.class)
        public ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
            //show 404 Not found with custom error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found: " + ex.getMessage());
        }

        //Catch All Generic Exceptions which doesn't handle generally
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleGeneric(Exception ex) {
            //500 internal server error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong: " + ex.getMessage());
        }

}
