package br.com.postech.techchallenge.orderapi.service.internal;

import br.com.postech.techchallenge.orderapi.dto.product.CreateProductDto;
import br.com.postech.techchallenge.orderapi.dto.product.ProductDto;
import br.com.postech.techchallenge.orderapi.dto.product.UpdateProductDto;
import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
import br.com.postech.techchallenge.orderapi.exception.EntityNotFoundException;
import br.com.postech.techchallenge.orderapi.infrastructure.repositories.IProductJpaRepository;
import br.com.postech.techchallenge.orderapi.mapper.IProductMapper;
import br.com.postech.techchallenge.orderapi.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final IProductMapper productMapper;

    private final IProductJpaRepository productRepository;

    public ProductDto create(CreateProductDto productDto) {

        Product product = productMapper.toProduct(productDto);
        product.setEstimatedTime(Duration.ofMinutes(productDto.getEstimatedTime()));

        productRepository.save(product);

        return productMapper.toProductDto(product);
    }

    public List<ProductDto> findAllByCategoryName(String name) {

        List<Product> producties = productRepository.findAllByIsActiveTrueAndProductCategory(ProductCategory.fromDisplayName(name));

        return productMapper.toProductListDto(producties);
    }

    public Product findById(Long id) throws EntityNotFoundException {

        return productRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("The product was not found."));
    }

    public void delete(Long id) throws EntityNotFoundException {

        if (!productRepository.existsById(id)) throw new EntityNotFoundException("The product was not found.");

        productRepository.deleteById(id);

    }

    public void update(Long idProduct, UpdateProductDto productDto) throws EntityNotFoundException {

        Product product = findById(idProduct);

        product.update(productDto);

        productRepository.save(product);
    }

}
