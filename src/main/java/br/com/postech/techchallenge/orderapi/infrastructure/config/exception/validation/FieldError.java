package br.com.postech.techchallenge.orderapi.infrastructure.config.exception.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FieldError {

    private String fieldName;
    private String message;
}