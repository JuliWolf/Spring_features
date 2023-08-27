package com.spring.features.person;

import com.spring.features.fakerService.FakerServiceTemplate;
import com.spring.features.fakerService.ServiceTemplate;
import com.spring.features.fakerService.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * @author JuliWolf
 * @date 25.08.2023
 */
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
  @Mock
  private JpaPersonRepository jpaPersonRepository;

  @Mock
  private ServiceTemplate<User> fakerServiceTemplate = new FakerServiceTemplate<>();


  @InjectMocks
  private PersonService personService = new PersonServiceImpl();


  @Test
  public void getPersonsFromDatabase_TestReturnedValues () {
    ArrayList<Person> persons = new ArrayList<>() {{
      add(new Person(1, "first_name_1", "last_name_1", "first@gmail.com"));
      add(new Person(2, "first_name_2", "last_name_2", "second@gmail.com"));
      add(new Person(3, "first_name_3", "last_name_3", "second@gmail.com"));
    }};

    when(jpaPersonRepository.findAll()).thenReturn(persons);

    List<Person> personsFromDatabase = personService.getPersonsFromDatabase();

    Assertions.assertEquals(1, personsFromDatabase.get(0).getId());
    Assertions.assertEquals(3, personsFromDatabase.get(2).getId());
  }

  @Test
  public void getPersonsFromDatabase_TestEmptyResult () {
    ArrayList<Person> persons = new ArrayList<>();

    when(jpaPersonRepository.findAll()).thenReturn(persons);

    List<Person> personsFromDatabase = personService.getPersonsFromDatabase();

    Assertions.assertTrue(personsFromDatabase.isEmpty());
  }

  @Test
  public void getPersonById_TestNullValueFromJpaRepository_AndNewValueFromGenerated () {
    int searchId = 1;

    User generatedUser = new User(
        searchId,
        "faker_fistName",
        "faker_lastName",
        "firstName lastName",
        24,
        "sdg@gmail.com",
        "address",
        "some url"
    );

    Person personFromService = new Person(searchId, generatedUser.getFirst_name(), generatedUser.getLast_name(), generatedUser.getEmail());

    when(jpaPersonRepository.findById(searchId)).thenReturn(Optional.empty());
    when(jpaPersonRepository.saveWithCustomId(Mockito.any(Person.class))).thenReturn(personFromService);

    when(fakerServiceTemplate.getData("/user/generate", User.class))
        .thenReturn(new ResponseEntity<>(generatedUser, HttpStatus.OK));

    Person person = personService.getPersonById(searchId);

    Assertions.assertNotNull(person);
    Assertions.assertEquals(person.getId(), searchId);
  }

  @Test
  public void getPersonById_TestReturnedPersonValueFromRepository () {
    int searchId = 1;

    Person personFromDatabase = new Person(searchId, "fistName", "lastName", "sdg@gmail.com");

    when(jpaPersonRepository.findById(searchId)).thenReturn(Optional.of(personFromDatabase));

    Person person = personService.getPersonById(searchId);

    Assertions.assertEquals(searchId, person.getId());
  }
}
