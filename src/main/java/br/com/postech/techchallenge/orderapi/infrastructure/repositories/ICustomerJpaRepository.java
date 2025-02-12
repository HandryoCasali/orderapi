package br.com.postech.techchallenge.orderapi.infrastructure.repositories;

import br.com.postech.techchallenge.orderapi.models.Customer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerJpaRepository extends IJpaRepositoryBase<Customer> {
    Optional<Customer> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
