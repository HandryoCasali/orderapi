package br.com.postech.techchallenge.orderapi.infrastructure.repositories;

import br.com.postech.techchallenge.orderapi.models.BaseDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJpaRepositoryBase<T extends BaseDomain> extends JpaRepository<T, Long> {

}
