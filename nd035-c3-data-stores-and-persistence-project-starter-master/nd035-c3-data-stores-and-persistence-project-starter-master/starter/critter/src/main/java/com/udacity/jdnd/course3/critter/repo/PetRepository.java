package com.udacity.jdnd.course3.critter.repo;

import com.udacity.jdnd.course3.critter.entities.Pet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository
        extends CrudRepository<Pet, Long> {
    @Query("select p from Pet p where p.customer.id = :ownerId")
    public List<Pet> findPetsByOwnerId(Long ownerId);
}
