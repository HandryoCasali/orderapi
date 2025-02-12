package br.com.postech.techchallenge.orderapi.infrastructure.repositories;

import br.com.postech.techchallenge.orderapi.models.Combo;
import org.springframework.stereotype.Repository;

@Repository
public interface IComboJpaRepository extends IJpaRepositoryBase<Combo> {
}
