package br.com.postech.techchallenge.orderapi.service.external;

import br.com.postech.techchallenge.orderapi.models.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${payment.name}", url = "${payment.url}")
public interface IPaymentApiService {

    @PostMapping()
    void create(@RequestBody() Order order);

}

