package com.spring.features.fakerService;

import org.springframework.http.ResponseEntity;

/**
 * @author JuliWolf
 * @date 26.08.2023
 */
public interface ServiceTemplate<T> {
  public ResponseEntity<T> getData (String path, Class<T> clazz);
}
