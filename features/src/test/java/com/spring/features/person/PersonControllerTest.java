package com.spring.features.person;

import com.spring.features.person.error.PersonNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author JuliWolf
 * @date 25.08.2023
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PersonService personService;


  @Test
  public void getPersonsFromDatabaseResponse_TestSuccessResult () {
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/")
        .accept(MediaType.APPLICATION_JSON);

    try {
      mockMvc.perform(requestBuilder)
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andReturn();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void getPersonById_TestPersonFoundResult () {
    int searchId = 2;

    Person person = new Person(searchId, "fistName", "lastName", "sdg@gmail.com");

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/"+ person.getId())
        .accept(MediaType.APPLICATION_JSON);

    when(personService.getPersonById(person.getId())).thenReturn(person);

    try {
      mockMvc.perform(requestBuilder)
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(jsonPath("$.result.id", is(person.getId())))
          .andExpect(jsonPath("$.result.firstName", is(person.getFirstName())))
          .andExpect(jsonPath("$.result.lastName", is(person.getLastName())))
          .andExpect(jsonPath("$.result.email", is(person.getEmail())));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void getPersonById_TestPersonNotFoundError () {
    int searchId = 2;

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/"+ searchId)
        .accept(MediaType.APPLICATION_JSON);

    when(personService.getPersonById(searchId)).thenThrow(new PersonNotFoundException("Person is not found - " + searchId));

    try {
      mockMvc.perform(requestBuilder)
          .andExpect(MockMvcResultMatchers.status().isNotFound())
          .andReturn();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
