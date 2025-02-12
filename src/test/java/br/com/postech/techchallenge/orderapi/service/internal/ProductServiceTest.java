package br.com.postech.techchallenge.orderapi.service.internal;

import br.com.postech.techchallenge.orderapi.dto.product.CreateProductDto;
import br.com.postech.techchallenge.orderapi.dto.product.UpdateProductDto;
import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
import br.com.postech.techchallenge.orderapi.exception.EntityNotFoundException;
import br.com.postech.techchallenge.orderapi.infrastructure.repositories.IProductJpaRepository;
import br.com.postech.techchallenge.orderapi.mapper.IProductMapper;
import br.com.postech.techchallenge.orderapi.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService service;

    @Mock
    private IProductJpaRepository repository;

    @Spy
    private IProductMapper mapper = Mappers.getMapper(IProductMapper.class);

    @Test
    void create() {
        CreateProductDto dto = new CreateProductDto("Hamburguer", "Description", new BigDecimal(10), 0.0, ProductCategory.LANCHE, 60L);
        service.create(dto);
        verify(repository, times(1)).save(any(Product.class));
    }

    @Test
    void findAllByCategoryName() {
        Product entity = Product.builder()
                .name("Hamburguer").build();
        List<Product> entities = List.of(entity);
        when(repository.findAllByIsActiveTrueAndProductCategory(ProductCategory.LANCHE)).thenReturn(entities);

        var result = service.findAllByCategoryName("Lanche");
        assertAll(
                ()-> assertEquals(1, result.size()),
                ()-> assertEquals("Hamburguer", result.get(0).getName())
        );

    }

    @Test
    void findById() throws EntityNotFoundException {
        Product entity = Product.builder()
                .name("Hamburguer").build();
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);
        assertEquals("Hamburguer", result.getName());
    }

    @Test
    void findByIdWhenNotExistsProduct() throws EntityNotFoundException {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, ()-> service.findById(1L));
    }

    @Test
    void delete() throws EntityNotFoundException {
        when(repository.existsById(1L)).thenReturn(true);
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);

    }

    @Test
    void deleteWhenNotExistsProduct() throws EntityNotFoundException {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, ()-> service.delete(1L));
        verify(repository, times(0)).deleteById(1L);

    }

    @Test
    void update() throws EntityNotFoundException {
        UpdateProductDto dto = new UpdateProductDto("Hamburguer", "Description", new BigDecimal(10), 0.0);
        Product entity = Product.builder()
                .name("Hamburguer").build();

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.update(1L, dto);
        verify(repository, times(1)).save(any(Product.class));
    }
}