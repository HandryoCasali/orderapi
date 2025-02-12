package br.com.postech.techchallenge.orderapi.service.internal;

import br.com.postech.techchallenge.orderapi.dto.customer.CreateCustomerDto;
import br.com.postech.techchallenge.orderapi.exception.EntityNotFoundException;
import br.com.postech.techchallenge.orderapi.infrastructure.repositories.ICustomerJpaRepository;
import br.com.postech.techchallenge.orderapi.mapper.ICustomerMapper;
import br.com.postech.techchallenge.orderapi.models.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService service;
    @Mock
    private ICustomerJpaRepository customerRepository;
    @Spy
    private ICustomerMapper customerMapper = Mappers.getMapper(ICustomerMapper.class);

    @Test
    void create() {
        CreateCustomerDto dto = new CreateCustomerDto("Customer", "cpf", "email");
        when(customerRepository.existsByCpf(anyString())).thenReturn(false);

        service.create(dto);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void createWhenExistsByCpf() {
        CreateCustomerDto dto = new CreateCustomerDto("Customer", "cpf", "email");
        when(customerRepository.existsByCpf(anyString())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, ()-> service.create(dto));
        verify(customerRepository, times(0)).save(any(Customer.class));
    }

    @Test
    void find() throws EntityNotFoundException {
        Customer customer = new Customer("Customer", "cpf", "email");
        when(customerRepository.findByCpf("cpf")).thenReturn(Optional.of(customer));

        var result = service.find("cpf");

        assertEquals("Customer", result.getName());
    }

    @Test
    void findWhenCpfNotExists() {
        when(customerRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> service.find(anyString()));
    }
}