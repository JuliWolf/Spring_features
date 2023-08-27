package com.example.randomrestdata.user;

import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JuliWolf
 * @date 25.08.2023
 */
@RestController
@RequestMapping("/user")
public class UserController {

  private Faker faker = new Faker();

  @GetMapping("/generate")
  public User getGeneratetUser () {
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();

    return User.builder()
        .id(faker.number().randomDigitNotZero())
        .first_name(firstName)
        .last_name(lastName)
        .fio(firstName + " " + lastName)
        .address(faker.address().fullAddress())
        .age(faker.number().numberBetween(1, 90))
        .email(faker.bothify("????##@gmail.com"))
        .avatar(faker.avatar().image())
        .build();
  }
}
