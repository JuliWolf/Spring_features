package com.spring.features.person;

import com.spring.features.fakerService.ServiceTemplate;
import com.spring.features.fakerService.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author JuliWolf
 * @date 24.08.2023
 */
@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  private JpaPersonRepository jpaPersonRepository;

  @Autowired
  private ServiceTemplate<User> fakerServiceTemplate;

  @Override
  public List<Person> getPersonsFromDatabase() {
    return jpaPersonRepository.findAll();
  }

  @Override
  public Person getPersonById(int personId) {
//    Optional<Person> person = jpaPersonRepository.findById(personId);
//
//    if (person.isPresent()) {
//      return person.get();
//    }
//
//    ResponseEntity<User> fakerResponse = fakerServiceTemplate.getData("/user/generate", User.class);
//
//    if (fakerResponse.hasBody()) {
//      User user = fakerResponse.getBody();
//
//      Person newPerson = new Person();
//      newPerson.setId(personId);
//      newPerson.setEmail(user.getEmail());
//      newPerson.setFirstName(user.getFirst_name());
//      newPerson.setLastName(user.getLast_name());
//
//      Person addedPerson = jpaPersonRepository.saveWithCustomId(newPerson);
//      System.out.println(addedPerson.getId());
//
//      return newPerson;
//    }

    return null;
  }
}
