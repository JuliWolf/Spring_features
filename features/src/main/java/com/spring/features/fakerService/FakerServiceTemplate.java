package com.spring.features.fakerService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author JuliWolf
 * @date 25.08.2023
 */
@Component
public class FakerServiceTemplate<T> implements ServiceTemplate<T> {
  private RestTemplate restTemplate = new RestTemplate();

  @Value("${fakerTemplateUrl}")
  private String fakerTemplateUrl;

  public ResponseEntity<T> getData (String path, Class<T> clazz) {
    return restTemplate.getForEntity(fakerTemplateUrl + path, clazz);
  }
}
