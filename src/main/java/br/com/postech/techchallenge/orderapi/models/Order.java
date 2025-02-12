package br.com.postech.techchallenge.orderapi.models;


import br.com.postech.techchallenge.orderapi.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order extends BaseDomain {
    @OneToMany(cascade = CascadeType.ALL)
    private List<Combo> combos = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.CREATED;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;
    private LocalDateTime finishedTime;

    public void addCombo(Combo combo) {
        combos.add(combo);
        increasePrice(combo.getTotalPrice());
    }

    private void increasePrice(BigDecimal price) {
        this.totalPrice = totalPrice.add(price);
    }

    public void updateOrderStatus(OrderStatus status) {
        this.orderStatus = status;
        if (OrderStatus.FINISHED == status)
            updateFinishedTime();
    }

    private void updateFinishedTime() {
        this.finishedTime = LocalDateTime.now();
    }
}
