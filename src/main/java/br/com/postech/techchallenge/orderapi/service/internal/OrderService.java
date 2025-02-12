package br.com.postech.techchallenge.orderapi.service.internal;

import br.com.postech.techchallenge.orderapi.dto.combo.CreateComboDto;
import br.com.postech.techchallenge.orderapi.dto.order.CreateOrderDto;
import br.com.postech.techchallenge.orderapi.dto.order.DetailsOrderDto;
import br.com.postech.techchallenge.orderapi.dto.order.OrderDto;
import br.com.postech.techchallenge.orderapi.enums.OrderStatus;
import br.com.postech.techchallenge.orderapi.exception.EntityNotFoundException;
import br.com.postech.techchallenge.orderapi.infrastructure.repositories.ICustomerJpaRepository;
import br.com.postech.techchallenge.orderapi.infrastructure.repositories.IOrderJpaRepository;
import br.com.postech.techchallenge.orderapi.mapper.IOrderMapper;
import br.com.postech.techchallenge.orderapi.models.Combo;
import br.com.postech.techchallenge.orderapi.models.Customer;
import br.com.postech.techchallenge.orderapi.models.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final IOrderJpaRepository orderRepository;
    private final IOrderMapper orderMapper;
    private final ComboService comboService;
    private final ICustomerJpaRepository customerRepository;

    public OrderDto create(CreateOrderDto createOrderDto) throws EntityNotFoundException {

        Order order = new Order();

        for (CreateComboDto comboDto : createOrderDto.getCombos()) {

            Combo combo = comboService.create(comboDto);

            order.addCombo(combo);
        }

        addCustomerInOrder(order, createOrderDto);

        Order orderSaved = orderRepository.save(order);

        //paymentService.Create(orderSaved);

        return orderMapper.toOrderDto(orderSaved);
    }

    public List<OrderDto> findAllByCPF(String cpf) {

        List<Order> orders = orderRepository.findAllByCustomerCpf(cpf);

        return orderMapper.toOrdersDto(orders);
    }

    public DetailsOrderDto findById(Long id) throws EntityNotFoundException {

        Order order = orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("The order was not found."));

        return orderMapper.toDetailsOrderDto(order);
    }

    public List<DetailsOrderDto> findByOrderStatus(OrderStatus orderStatus) {

        List<Order> orders = orderRepository.findAllByOrderStatus(orderStatus);

        return orderMapper.toDetailsOrdersDto(orders);
    }

    public List<DetailsOrderDto> findAll() {

        var response = orderRepository.findAll();

        return orderMapper.toDetailsOrdersDto(response);
    }

    public void updateStatus(Long id, OrderStatus orderStatus) throws EntityNotFoundException {

        Order order = orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("The order was not found."));

        if(orderStatus.equals(OrderStatus.RECEIVED)){
            //Mandar pra cozinha
        }

        order.updateOrderStatus(orderStatus);

        orderRepository.save(order);
    }

    private void addCustomerInOrder(Order order, CreateOrderDto createOrderDto) throws EntityNotFoundException {

        Customer customer;
        try {
            customer = customerRepository.findById(createOrderDto.getCustomerId())
                    .orElseThrow(()-> new EntityNotFoundException("The customer was not found."));
        } catch (EntityNotFoundException e) {
            customer = customerRepository.save(new Customer());
        }

        order.setCustomer(customer);
    }

}
