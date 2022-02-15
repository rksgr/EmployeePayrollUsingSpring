package com.example.springemployeepayroll.service;

import com.example.springemployeepayroll.DTO.EmployeePayrollDTO;
import com.example.springemployeepayroll.Entity.EmployeePayrollData;
import com.example.springemployeepayroll.repository.EmployeePayrollRepository;
import com.example.springemployeepayroll.exception.EmployeePayrollException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeePayrollService implements IEmployeePayrollService{

    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    // Store the list in the RAM
    private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

    @Override
    public List<EmployeePayrollData> getEmployeePayrollData(){
        return employeePayrollList;
    }

    @Override
    public EmployeePayrollData getEmployeePayrollDataById(int empId){
        return employeePayrollList.stream()
                .filter(empData->empData.getEmployeeId() == empId)
                .findFirst()
                .orElseThrow(()-> new EmployeePayrollException("Employee not found"));
    }

    @Override
    public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO employeePayrollDTO){
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = new EmployeePayrollData(employeePayrollDTO);
        log.debug("Emp data "+ employeePayrollData.toString());
        employeePayrollList.add(employeePayrollData);
        return employeePayrollRepository.save(employeePayrollData);
    }
    @Override
    public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO employeePayrollDTO){
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollDataById(empId);
        employeePayrollData.setName(employeePayrollDTO.name);
        employeePayrollData.setSalary(employeePayrollDTO.salary);
        employeePayrollList.set(empId-1, employeePayrollData);
        return employeePayrollData;
    }
    @Override
    public void deleteEmployeePayrollData(int empId){
        employeePayrollList.remove(empId-1);
    }
}
