package br.com.postech.techchallenge.orderapi.dto.combo;

import br.com.postech.techchallenge.orderapi.dto.addon.AddonDto;
import br.com.postech.techchallenge.orderapi.dto.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ComboDto {
    private ProductDto product;
    private List<AddonDto> addons;
    private BigDecimal totalPrice;
}
