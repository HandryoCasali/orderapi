package br.com.postech.techchallenge.orderapi.mapper;

import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IProductCategoryMapper {

    IProductCategoryMapper INSTANCE = Mappers.getMapper(IProductCategoryMapper.class);

    default String map(ProductCategory productCategory) {
        return productCategory != null ? productCategory.getDisplayName() : null;
    }
    default ProductCategory map(String productCategoryName) {
        return productCategoryName != null ? ProductCategory.valueOf(productCategoryName) : null;
    }

}
