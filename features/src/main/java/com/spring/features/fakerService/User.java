package com.spring.features.fakerService;

import lombok.*;

/**
 * @author JuliWolf
 * @date 25.08.2023
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  private int id;

  private String first_name;

  private String last_name;

  private String fio;

  private int age;

  private String email;

  private String address;

  private String avatar;
}
