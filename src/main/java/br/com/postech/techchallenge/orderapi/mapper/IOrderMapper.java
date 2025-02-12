package br.com.postech.techchallenge.orderapi.mapper;

import br.com.postech.techchallenge.orderapi.dto.order.DetailsOrderDto;
import br.com.postech.techchallenge.orderapi.dto.order.OrderDto;
import br.com.postech.techchallenge.orderapi.models.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IProductMapper.class, IAddonMapper.class, IOrderStatusMapper.class})
public interface IOrderMapper {

    OrderDto toOrderDto(Order order);

    List<OrderDto> toOrdersDto(List<Order> domains);

    List<DetailsOrderDto> toDetailsOrdersDto(List<Order> domains);

    DetailsOrderDto toDetailsOrderDto(Order order);
}
