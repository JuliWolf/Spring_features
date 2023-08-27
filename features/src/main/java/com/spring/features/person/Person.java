package com.spring.features.person;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author JuliWolf
 * @date 24.08.2023
 */
@Entity
@Table(name="person")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @Column(name="\"firstName\"")
  private String firstName;

  @Column(name="\"lastName\"")
  private String lastName;

  @Column(name="email")
  private String email;
}
