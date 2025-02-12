package br.com.postech.techchallenge.orderapi.controller;

import br.com.postech.techchallenge.orderapi.dto.product.CreateProductDto;
import br.com.postech.techchallenge.orderapi.dto.product.ProductDto;
import br.com.postech.techchallenge.orderapi.dto.product.UpdateProductDto;
import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
import br.com.postech.techchallenge.orderapi.exception.EntityNotFoundException;
import br.com.postech.techchallenge.orderapi.service.internal.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController controller;
    @Mock
    private ProductService productService;

    @Test
    void create() throws EntityNotFoundException {
        CreateProductDto createDto = new CreateProductDto("Hamburguer", "Descrição", BigDecimal.TEN, 0.0, ProductCategory.LANCHE, 60L);
        ProductDto productDto = new ProductDto();
        when(productService.create(createDto)).thenReturn(productDto);

        var result = controller.create(createDto);

        assertAll(
                ()-> assertEquals(HttpStatus.CREATED, result.getStatusCode()),
                ()-> assertEquals(productDto, result.getBody())
        );
    }

    @Test
    void getAllByProductCategoryName() {
        ProductDto productDto = new ProductDto();
        List<ProductDto> productDtos = List.of(productDto);
        when(productService.findAllByCategoryName("Lanche")).thenReturn(productDtos);

        var result = controller.getAllByProductCategoryName("Lanche");

        assertAll(
                ()-> assertEquals(HttpStatus.OK, result.getStatusCode()),
                ()-> assertEquals(productDtos, result.getBody())
        );
    }

    @Test
    void update() throws EntityNotFoundException {
        UpdateProductDto dto = new UpdateProductDto();

        var result = controller.update(1L, dto);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void delete() throws EntityNotFoundException {
        var result = controller.delete(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}