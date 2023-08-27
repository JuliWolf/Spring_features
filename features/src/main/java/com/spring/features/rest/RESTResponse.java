package com.spring.features.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author JuliWolf
 * @date 24.08.2023
 */
@Setter
@Getter
@Builder
public class RESTResponse<T> {
  private int errorCode;

  private String errorMessage;

  private long timestamp;

  private T result;
}
