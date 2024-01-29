package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repo.EmployeeRepository;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }
    public List<Employee> findAll(){
        return (List<Employee>) employeeRepository.findAll();
    }

    //ADD EXCEPTION IN FUTURE
    public Employee findById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public List<Employee> findBySkillsAndDaysAvailable(Set<EmployeeSkill> skills, DayOfWeek day){
        List<Employee> employees = employeeRepository.findAllByDaysAvailableContaining(day);
        List<Employee> filteredEmployees = new ArrayList<>();

        employees.forEach(employee -> {
            if (employee.getSkills().containsAll(skills))
                filteredEmployees.add(employee);
        });

        return filteredEmployees;
    }
}
