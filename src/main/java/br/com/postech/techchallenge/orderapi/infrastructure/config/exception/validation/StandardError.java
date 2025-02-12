package br.com.postech.techchallenge.orderapi.infrastructure.config.exception.validation;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class StandardError {

    private LocalDateTime timestamp;
    private String path;
    private Integer status;
    private String error;
    private String message;
}