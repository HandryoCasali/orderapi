package br.com.postech.techchallenge.orderapi.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String orderStatus;
    private BigDecimal totalPrice;
}
