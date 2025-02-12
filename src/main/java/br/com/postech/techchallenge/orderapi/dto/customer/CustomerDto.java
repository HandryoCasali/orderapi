package br.com.postech.techchallenge.orderapi.dto.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private Long id;
    private String name;
    private String cpf;
    private String email;
}
