package com.udacity.jdnd.course3.critter.service;
import com.udacity.jdnd.course3.critter.repo.CustomerRepository;
import com.udacity.jdnd.course3.critter.entities.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }
    public List<Customer> findAll(){
        return (List<Customer>) customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).get();//orElseThrow( () -> new Exception("Customer Not Found"));
    }
}
