package com.spring.features.person;

import java.util.List;

/**
 * @author JuliWolf
 * @date 24.08.2023
 */
public interface PersonService {
  List<Person> getPersonsFromDatabase();

  Person getPersonById(int personId);
}
