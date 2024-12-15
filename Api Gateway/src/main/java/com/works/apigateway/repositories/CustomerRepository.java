package com.works.apigateway.repositories;

import com.works.apigateway.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsernameEqualsIgnoreCase(String username);
}
