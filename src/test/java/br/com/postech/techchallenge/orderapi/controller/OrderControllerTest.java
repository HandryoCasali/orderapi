package br.com.postech.techchallenge.orderapi.controller;

import br.com.postech.techchallenge.orderapi.dto.addon.AddonDto;
import br.com.postech.techchallenge.orderapi.dto.combo.ComboDto;
import br.com.postech.techchallenge.orderapi.dto.customer.CustomerDto;
import br.com.postech.techchallenge.orderapi.dto.order.CreateOrderDto;
import br.com.postech.techchallenge.orderapi.dto.order.DetailsOrderDto;
import br.com.postech.techchallenge.orderapi.dto.order.OrderDto;
import br.com.postech.techchallenge.orderapi.dto.product.ProductDto;
import br.com.postech.techchallenge.orderapi.enums.OrderStatus;
import br.com.postech.techchallenge.orderapi.exception.EntityNotFoundException;
import br.com.postech.techchallenge.orderapi.service.internal.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderService orderService;

    @Test
    void create() throws EntityNotFoundException {
        CreateOrderDto createOrderDto = new CreateOrderDto();
        OrderDto orderDto = new OrderDto(1L, OrderStatus.RECEIVED.name(), BigDecimal.TEN);
        when(orderService.create(createOrderDto)).thenReturn(orderDto);

        var result = orderController.create(createOrderDto);

        assertAll(
                ()-> assertEquals(HttpStatus.CREATED, result.getStatusCode()),
                ()-> assertEquals(orderDto, result.getBody())
        );
    }

    @Test
    void getAllOrdersByCostumer() {
        OrderDto orderDto = new OrderDto(1L, OrderStatus.RECEIVED.name(), BigDecimal.TEN);
        List<OrderDto> dtos = List.of(orderDto);
        when(orderService.findAllByCPF("cpf")).thenReturn(dtos);

        var result = orderController.getAllOrdersByCostumer("cpf");

        assertAll(
                ()-> assertEquals(HttpStatus.OK, result.getStatusCode()),
                ()-> assertEquals(dtos, result.getBody())
        );
    }

    @Test
    void getAllOrdersByStatus() throws EntityNotFoundException {
        DetailsOrderDto detailsOrderDto = createDetailsOrderDto();
        List<DetailsOrderDto> dtos = List.of(detailsOrderDto);
        when(orderService.findByOrderStatus(OrderStatus.RECEIVED)).thenReturn(dtos);

        var result = orderController.getAllOrdersByStatus(OrderStatus.RECEIVED);

        assertAll(
                ()-> assertEquals(HttpStatus.OK, result.getStatusCode()),
                ()-> assertEquals(dtos, result.getBody())
        );
    }

    @Test
    void getAllOrders() throws EntityNotFoundException {
        DetailsOrderDto detailsOrderDto = createDetailsOrderDto();
        List<DetailsOrderDto> dtos = List.of(detailsOrderDto);
        when(orderService.findAll()).thenReturn(dtos);

        var result = orderController.getAllOrders();

        assertAll(
                ()-> assertEquals(HttpStatus.OK, result.getStatusCode()),
                ()-> assertEquals(dtos, result.getBody())
        );
    }

    @Test
    void updateStatus() throws EntityNotFoundException {
        var result = orderController.updateStatus(1L, OrderStatus.RECEIVED);

        assertAll(
                ()-> assertEquals(HttpStatus.OK, result.getStatusCode())
        );
    }

    @Test
    void getOrderById() throws EntityNotFoundException {
        DetailsOrderDto detailsOrderDto = createDetailsOrderDto();
        when(orderService.findById(anyLong())).thenReturn(detailsOrderDto);

        var result = orderController.getOrderById(1L);

        assertAll(
                ()-> assertEquals(HttpStatus.OK, result.getStatusCode()),
                ()-> assertEquals(detailsOrderDto, result.getBody())
        );
    }

    DetailsOrderDto createDetailsOrderDto(){
        AddonDto addonDto = new AddonDto(1L, "bacon", BigDecimal.TEN, 0.0, "Lanche");
        ComboDto comboDto = new ComboDto();
        comboDto.setProduct(new ProductDto());
        comboDto.setAddons(List.of(addonDto));
        comboDto.setTotalPrice(BigDecimal.TEN);
        CustomerDto customerDto = new CustomerDto();
        return new DetailsOrderDto(1L, List.of(comboDto), BigDecimal.TEN, OrderStatus.CREATED, customerDto, "", "");
    }
}