package com.example.Account.mapper;

import com.example.Account.Model.Customer;
import com.example.Account.dto.CustomerDto;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer,CustomerDto customerDto){
       customerDto.setName(customer.getName());
       customerDto.setEmail(customer.getEmail());
       customerDto.setMobileNumber(customer.getMobileNo());
       return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto,Customer customer){
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNo(customerDto.getMobileNumber());
        return customer;
    }
}
