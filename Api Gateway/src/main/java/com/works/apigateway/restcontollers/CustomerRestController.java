package com.works.apigateway.restcontollers;

import com.works.apigateway.entities.Customer;
import com.works.apigateway.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("admin")
public class CustomerRestController {
    final CustomerService customerService;
    @PostMapping("register")
    public Customer register(@RequestBody Customer customer) {
        return customerService.register(customer);
    }
}
