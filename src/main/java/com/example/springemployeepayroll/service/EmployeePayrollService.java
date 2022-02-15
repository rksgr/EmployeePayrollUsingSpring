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

    @Override
    public List<EmployeePayrollData> getEmployeePayrollData(){
        return employeePayrollRepository.findAll();
    }

    @Override
    public EmployeePayrollData getEmployeePayrollDataById(int empId){
        return employeePayrollRepository
                .findById(empId)
                .orElseThrow(()->new EmployeePayrollException("Employee with employe id"+ empId
               +"does not exist!"));
    }

    @Override
    public List<EmployeePayrollData> getEmployeesByDepartment(String department){
        return employeePayrollRepository.getEmployeesByDepartment(department);
    }
    @Override
    public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO employeePayrollDTO){
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = new EmployeePayrollData(employeePayrollDTO);
        log.debug("Emp data "+ employeePayrollData.toString());
        return employeePayrollRepository.save(employeePayrollData);
    }
    @Override
    public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO employeePayrollDTO){
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollDataById(empId);
        employeePayrollData.updateEmployeePayrollData(employeePayrollDTO);
        return employeePayrollRepository.save(employeePayrollData);
    }
    @Override
    public void deleteEmployeePayrollData(int empId){
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollDataById(empId);
        employeePayrollRepository.delete(employeePayrollData);
    }
}
