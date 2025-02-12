package br.com.postech.techchallenge.orderapi.mapper;

import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class IProductCategoryMapperTest {

    private IProductCategoryMapper mapper = IProductCategoryMapper.INSTANCE;

    @Test
    void mapToString() {
        String result = mapper.map(ProductCategory.LANCHE);
        assertEquals(ProductCategory.LANCHE.getDisplayName(), result);
    }

    @Test
    void mapToProductCategory() {
        var result = mapper.map("LANCHE");
        assertEquals(ProductCategory.LANCHE, result);
    }
}