package br.com.postech.techchallenge.orderapi.mapper;

import br.com.postech.techchallenge.orderapi.dto.combo.ComboDto;
import br.com.postech.techchallenge.orderapi.dto.combo.CreateComboDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IComboMapper {


    ComboDto toComboDto(CreateComboDto createCombo);
}
