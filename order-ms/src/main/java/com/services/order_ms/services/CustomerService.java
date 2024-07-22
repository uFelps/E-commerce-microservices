package com.services.order_ms.services;

import com.services.order_ms.dtos.CustomerDTO;
import com.services.order_ms.entities.Customer;
import com.services.order_ms.repositories.CustomerRepository;
import com.services.order_ms.services.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer customer = new Customer(null, dto.name(), dto.cpf(), dto.email());

        customer = repository.save(customer);

        return new CustomerDTO(customer.getId(), customer.getName(), customer.getCpf(), customer.getEmail());
    }

    public Customer getCustomerReferenceById(Long id) {
        try{
            return repository.getReferenceById(id);
        }
        catch (Exception e){
            throw new DataNotFoundException("Customer not found! ID: " + id);
        }

    }
}
