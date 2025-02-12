package br.com.postech.techchallenge.orderapi.dto.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerDto {
    private String name;
    @NotBlank
    private String cpf;
    private String email;
}
