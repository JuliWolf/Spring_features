package com.spring.features.person;

import com.spring.features.person.error.PersonNotFoundException;
import com.spring.features.rest.RESTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JuliWolf
 * @date 24.08.2023
 */
@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private PersonService personService;

  @GetMapping("/")
  public RESTResponse<List<Person>> getPersonsFromDatabase () {
    List<Person> persons = personService.getPersonsFromDatabase();

    return RESTResponse
        .<List<Person>>builder()
        .timestamp(System.currentTimeMillis())
        .errorMessage("")
        .errorCode(HttpStatus.OK.value())
        .result(persons)
        .build();
  }

  @GetMapping("/{personId}")
  public RESTResponse<Person> getPersonById (@PathVariable int personId) {
    Person person = personService.getPersonById(personId);

    if (person == null) {
      throw new PersonNotFoundException("Person is not found - " + personId);
    }

    return RESTResponse
        .<Person>builder()
        .timestamp(System.currentTimeMillis())
        .errorMessage("")
        .errorCode(HttpStatus.OK.value())
        .result(person)
        .build();
  }
}
