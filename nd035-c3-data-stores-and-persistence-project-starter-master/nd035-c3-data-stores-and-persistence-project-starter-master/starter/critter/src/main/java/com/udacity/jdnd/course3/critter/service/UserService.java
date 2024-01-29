package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repo.CustomerRepository;
import com.udacity.jdnd.course3.critter.repo.EmployeeRepository;
import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class UserService {
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    public UserService(CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    public Long save(Customer customer) {
        customerRepository.save(customer);

        return customer.getId();
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getUser(Long ownerId) {
        return customerRepository.findById(ownerId);
    }

    public Optional<Employee> getEmployee(long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findEmployeesForService(DayOfWeek desiredDate, Set<EmployeeSkill> desiredSkills) {
        Iterable<Employee> employeeIt = employeeRepository.findAll();
        List<Employee> employeesForService = new ArrayList<>();
        for (Employee employee: employeeIt) {
            if (employee.getDaysAvailable().contains(desiredDate) && employee.getSkills().containsAll(desiredSkills)) {
                employeesForService.add(employee);
            }
        }

        return employeesForService;
    }
}
