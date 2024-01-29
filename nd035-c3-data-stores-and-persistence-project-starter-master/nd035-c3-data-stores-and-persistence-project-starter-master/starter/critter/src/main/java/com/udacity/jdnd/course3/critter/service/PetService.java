package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repo.PetRepository;
import com.udacity.jdnd.course3.critter.entities.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class PetService {

    private final PetRepository petRepository;
    private final CustomerService customerService;

    public PetService(PetRepository petRepository, CustomerService customerService) {
        this.petRepository = petRepository;
        this.customerService = customerService;
    }

    public Pet save(Pet pet){
        Pet savedPet = petRepository.save(pet);
        Customer customer = savedPet.getCustomer();
        Set<Pet> customerPets = customer.getPets();

        if (customerPets == null)
            customerPets = new HashSet<>();

        customerPets.add(savedPet);
        customer.setPets(customerPets);
        customerService.save(customer);
        return savedPet;
    }
    public List<Pet> findAll(){
        return (List<Pet>) petRepository.findAll();
    }
    public Pet findById(Long id){
        Optional<Pet> optionalPet =  petRepository.findById(id);
        //orElseThrow( () -> new Exception("Pet Not found") );
        Pet pet = null;
        try {
            pet =  optionalPet.get();
        }catch (Exception e){

        }
        return pet;
    }

    public List<Pet> findPetsByOwnerId(Long ownerId){
        return petRepository.findPetsByOwnerId(ownerId);
    }
}
