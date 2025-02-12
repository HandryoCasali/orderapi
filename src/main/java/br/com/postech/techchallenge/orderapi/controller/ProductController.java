package br.com.postech.techchallenge.orderapi.controller;

import br.com.postech.techchallenge.orderapi.dto.product.CreateProductDto;
import br.com.postech.techchallenge.orderapi.dto.product.ProductDto;
import br.com.postech.techchallenge.orderapi.dto.product.UpdateProductDto;
import br.com.postech.techchallenge.orderapi.exception.EntityNotFoundException;
import br.com.postech.techchallenge.orderapi.service.internal.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody @Valid CreateProductDto createDto){
        var dto = productService.create(createDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllByProductCategoryName(@RequestParam String categoryName) {
        var dtos = productService.findAllByCategoryName(categoryName);
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody UpdateProductDto updateDto) throws EntityNotFoundException {
        productService.update(id, updateDto);
        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws EntityNotFoundException {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }
}
