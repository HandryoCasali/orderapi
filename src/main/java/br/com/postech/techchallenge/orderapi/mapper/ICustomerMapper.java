package br.com.postech.techchallenge.orderapi.mapper;

import br.com.postech.techchallenge.orderapi.dto.customer.CreateCustomerDto;
import br.com.postech.techchallenge.orderapi.dto.customer.CustomerDto;
import br.com.postech.techchallenge.orderapi.models.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {
    Customer toCustomer(CreateCustomerDto dto);

    CustomerDto toCustomerDto(Customer customerSaved);
}
