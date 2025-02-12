package br.com.postech.techchallenge.orderapi.dto.addon;

import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddonDto {
    @NotBlank
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private ProductCategory productCategory;
    @NotNull
    private Double discountPercent;
}
