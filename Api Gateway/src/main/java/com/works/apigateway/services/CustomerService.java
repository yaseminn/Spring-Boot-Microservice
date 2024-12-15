package com.works.apigateway.services;

import com.works.apigateway.entities.Customer;
import com.works.apigateway.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CustomerService {
    final CustomerRepository customerRepository;
    final PasswordEncoder passwordEncoder;
    public Customer register(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findByUsernameEqualsIgnoreCase(customer.getUsername());
        if (optionalCustomer.isPresent()) {
            return null;
        }
        String newPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(newPassword);
        return customerRepository.save(customer);
    }
}
