package br.com.postech.techchallenge.orderapi.dto.product;

import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateProductDto {
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private BigDecimal price;

    @NotNull
    private Double discountPercent;

    @NotNull
    private ProductCategory productCategory;

    @NotNull
    private Long estimatedTime;

}
