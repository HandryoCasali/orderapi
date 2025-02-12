package br.com.postech.techchallenge.orderapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer extends BaseDomain {
    private String name;
    private String cpf;
    private String email;
}
