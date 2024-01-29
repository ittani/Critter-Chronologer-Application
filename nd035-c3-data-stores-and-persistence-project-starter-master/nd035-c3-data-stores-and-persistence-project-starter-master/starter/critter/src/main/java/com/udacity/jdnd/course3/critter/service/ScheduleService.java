package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repo.ScheduleRepository;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule save(Schedule schedule){
        Schedule savedSchedule = scheduleRepository.save(schedule);


        //ADD bidirectional association, i guess...
        return savedSchedule;
    }

    public List<Schedule> findAll(){
        return (List<Schedule>) scheduleRepository.findAll();
    }

    public List<Schedule> findAllByPetsContaining(Long petId){
        return scheduleRepository.findAllByPetsId(petId);
    }

    public List<Schedule> findAllByEmployeesContaining(Long employeeId){
        return scheduleRepository.findAllByEmployeesId(employeeId);
    }

    public List<Schedule> findAllByPetsCustomerId(Long customerId){
        return scheduleRepository.findAllByPetsCustomerId(customerId);
    }



}
