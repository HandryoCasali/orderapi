package br.com.postech.techchallenge.orderapi.dto.combo;

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
public class CreateComboDto {
    @NotNull
    private Long productId;
    private List<Long> addonsId;
}
