package com.spring.features.person.error;

import com.spring.features.person.Person;
import com.spring.features.rest.RESTResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author JuliWolf
 * @date 27.08.2023
 */
@ControllerAdvice
public class PersonRestExceptionHandler {
  @ExceptionHandler
  public ResponseEntity<RESTResponse<Person>> handleException (PersonNotFoundException exception) {
    RESTResponse<Person> error = RESTResponse
        .<Person>builder()
        .timestamp(System.currentTimeMillis())
        .errorMessage("Person not found")
        .errorCode(HttpStatus.NOT_FOUND.value())
        .result(null)
        .build();

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<RESTResponse<Person>> handleException (Exception exception) {
    RESTResponse<Person> error = RESTResponse
        .<Person>builder()
        .timestamp(System.currentTimeMillis())
        .errorMessage("Bad request")
        .errorCode(HttpStatus.BAD_REQUEST.value())
        .result(null)
        .build();

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
