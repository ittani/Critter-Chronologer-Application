package com.udacity.jdnd.course3.critter.repo;

import com.udacity.jdnd.course3.critter.entities.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository
        extends CrudRepository<Schedule, Long> {
    List<Schedule> findAllByPetsId(Long petId);

    List<Schedule> findAllByEmployeesId(Long employeeId);

    List<Schedule> findAllByPetsCustomerId(Long id);
}
