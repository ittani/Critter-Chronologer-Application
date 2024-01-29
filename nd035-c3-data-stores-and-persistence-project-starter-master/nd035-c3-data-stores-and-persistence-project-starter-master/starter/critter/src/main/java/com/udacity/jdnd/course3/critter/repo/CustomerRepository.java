package com.udacity.jdnd.course3.critter.repo;

import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository
        extends CrudRepository<Customer, Long> {
    public List<Pet> findPetsById(Long id);
}
