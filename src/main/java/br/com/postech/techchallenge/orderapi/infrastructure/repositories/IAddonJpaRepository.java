package br.com.postech.techchallenge.orderapi.infrastructure.repositories;

import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
import br.com.postech.techchallenge.orderapi.models.Addon;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAddonJpaRepository extends IJpaRepositoryBase<Addon> {
    List<Addon> findAllByIsActiveTrueAndProductCategory(ProductCategory productCategory);

    @Override
    @Modifying
    @Query("UPDATE Addon a SET a.isActive = false WHERE a.id = :id")
    void deleteById(@NonNull @Param("id") Long id);

    List<Addon> findAllByIsActiveFalse();
}
