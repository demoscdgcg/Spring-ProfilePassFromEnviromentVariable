package com.example.Account.repository;

import com.example.Account.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
    Optional<Customer> findByMobileNo(String mobileNo);
}
