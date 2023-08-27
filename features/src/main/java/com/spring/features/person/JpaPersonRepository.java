package com.spring.features.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author JuliWolf
 * @date 24.08.2023
 */
public interface JpaPersonRepository extends JpaRepository<Person, Integer> {

  @Query(value="insert into person(id, \"firstName\", \"lastName\", email) " +
      "VALUES (:person.id, :person.firstName, :person.lastName, :person.email);" +
      "SELECT * FROM person WHERE id = :person.id;"
      , nativeQuery = true)
  Person saveWithCustomId(@Param("person") Person person);
}
