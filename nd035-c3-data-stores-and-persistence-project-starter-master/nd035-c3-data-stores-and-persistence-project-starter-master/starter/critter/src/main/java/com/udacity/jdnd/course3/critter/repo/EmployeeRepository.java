package com.udacity.jdnd.course3.critter.repo;

import com.udacity.jdnd.course3.critter.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface EmployeeRepository
        extends CrudRepository<Employee, Long> {
    public List<Employee> findAllByDaysAvailableContaining(DayOfWeek day);
}
