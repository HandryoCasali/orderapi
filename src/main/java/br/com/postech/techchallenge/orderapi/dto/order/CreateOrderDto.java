package br.com.postech.techchallenge.orderapi.dto.order;

import br.com.postech.techchallenge.orderapi.dto.combo.CreateComboDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateOrderDto {

    @NotEmpty
    @NotNull
    private List<CreateComboDto> combos;
    private Long customerId;
}
