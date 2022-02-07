package com.example.springemployeepayroll.service;

import com.example.springemployeepayroll.DTO.EmployeePayrollDTO;
import com.example.springemployeepayroll.Entity.EmployeePayrollData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeePayrollService implements IEmployeePayrollService{
    @Override
    public List<EmployeePayrollData> getEmployeePayrollData(){
        List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
        employeePayrollDataList.add(new EmployeePayrollData(1, new EmployeePayrollDTO("Altaf",5000)));
        return employeePayrollDataList;
    }
}
