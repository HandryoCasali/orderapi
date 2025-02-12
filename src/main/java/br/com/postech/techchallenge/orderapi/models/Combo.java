package br.com.postech.techchallenge.orderapi.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "combos")
public class Combo extends BaseDomain {

    @ManyToOne
    private Product product;
    @ManyToMany
    private List<Addon> addons = new ArrayList<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;

    public Combo(Product product) {

        if (product == null) throw new IllegalArgumentException("A Product is required.");

        this.product = product;

        increasePrice(product.getPriceDiscounted());
    }

    public void addAddon(Addon addon) {

        boolean categoryEquals = product.getProductCategory().equals(addon.getProductCategory());

        if (!categoryEquals)
            throw new IllegalArgumentException(addon.getName() + " não pode ser ao produto " + product.getName());

        addons.add(addon);

        increasePrice(addon.getPriceDiscounted());
    }

    private void increasePrice(BigDecimal price) {
        this.totalPrice = totalPrice.add(price);
    }

}
