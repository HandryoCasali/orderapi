package br.com.postech.techchallenge.orderapi.mapper;

import br.com.postech.techchallenge.orderapi.dto.product.CreateProductDto;
import br.com.postech.techchallenge.orderapi.dto.product.ProductDto;
import br.com.postech.techchallenge.orderapi.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = IProductCategoryMapper.class)
public interface IProductMapper {


    ProductDto toProductDto(Product product);


    @Mapping(target = "estimatedTime", ignore = true)
    Product toProduct(CreateProductDto dto);

    List<ProductDto> toProductListDto(List<Product> products);


}
