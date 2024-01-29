package com.udacity.jdnd.course3.critter.Controllers;

import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.assertj.core.util.Sets;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return convertScheduleToScheduleDTO(scheduleService.save(convertScheduleDTOToSchedule(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.findAll()
                .stream()
                .map(schedule -> convertScheduleToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.findAllByPetsContaining(petId)
                .stream()
                .map(schedule -> convertScheduleToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.findAllByEmployeesContaining(employeeId)
                .stream()
                .map(schedule -> convertScheduleToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.findAllByPetsCustomerId(customerId)
                .stream()
                .map(schedule -> convertScheduleToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    private Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO,schedule);
        if (scheduleDTO.getEmployeeIds() != null){
            schedule.setEmployees(Sets.newHashSet());
            schedule.setEmployees(
                    scheduleDTO.getEmployeeIds()
                            .stream()
                            .map( employeeId -> employeeService.findById(employeeId))
                            .collect(Collectors.toSet())
            );
        }
        if (scheduleDTO.getPetIds() != null){
            schedule.setPets(Sets.newHashSet());
            schedule.setPets(
                    scheduleDTO.getPetIds()
                            .stream()
                            .map(petId -> petService.findById(petId))
                            .collect(Collectors.toSet())
            );
        }
        return schedule;
    }

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule,scheduleDTO);
        if (schedule.getPets() != null){
            scheduleDTO.setPetIds(new ArrayList<>());
            schedule.getPets().forEach(pet -> scheduleDTO.getPetIds().add(pet.getId()));
        }
        if (schedule.getEmployees() != null){
            scheduleDTO.setEmployeeIds(new ArrayList<>());
            schedule.getEmployees().forEach(employee -> scheduleDTO.getEmployeeIds().add(employee.getId()));
        }
        return scheduleDTO;
    }
}
