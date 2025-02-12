package br.com.postech.techchallenge.orderapi.models;

import br.com.postech.techchallenge.orderapi.dto.product.UpdateProductDto;
import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Duration;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "producties")
public class Product extends BaseDomain {

    @Column(nullable = false)
    private String name;
    private String description;

    @Column(nullable = false)
    private BigDecimal price = BigDecimal.ZERO;
    private Double discountPercent = 0.0;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Duration estimatedTime;

    public void update(UpdateProductDto productDto) {
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.discountPercent = productDto.getDiscountPercent();
    }
    public BigDecimal getPriceDiscounted() {
        return price.multiply(BigDecimal.valueOf(1 - discountPercent));
    }
}
