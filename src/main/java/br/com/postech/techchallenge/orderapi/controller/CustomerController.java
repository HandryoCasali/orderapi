package br.com.postech.techchallenge.orderapi.controller;

import br.com.postech.techchallenge.orderapi.dto.customer.CreateCustomerDto;
import br.com.postech.techchallenge.orderapi.dto.customer.CustomerDto;
import br.com.postech.techchallenge.orderapi.exception.EntityNotFoundException;
import br.com.postech.techchallenge.orderapi.service.internal.CustomerService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerDto> getByCpf(@PathVariable String cpf) throws EntityNotFoundException {
        return ResponseEntity.ok(customerService.find(cpf));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody CreateCustomerDto dto) {
        customerService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }


}
