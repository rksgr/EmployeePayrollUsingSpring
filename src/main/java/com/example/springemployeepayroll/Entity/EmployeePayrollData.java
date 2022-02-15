package com.example.springemployeepayroll.Entity;

import com.example.springemployeepayroll.DTO.EmployeePayrollDTO;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
@Data
public class EmployeePayrollData {
    private long employeeId;

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$", message = "Employee name invalid")
    private String name;
    @Min(value=5000,message="Salary cannot be less than 5000")
    private long salary;

    private String gender;
    private LocalDate startDate;
    private String note;
    private String profilePic;
    private List<String> departments;


    public EmployeePayrollData(){}

    public EmployeePayrollData(long employeeId, EmployeePayrollDTO employeePayrollDTO) {
        this.employeeId = employeeId;
        this.updateEmployeePayrollData(employeePayrollDTO);
    }

    public void updateEmployeePayrollData(EmployeePayrollDTO employeePayrollDTO){
        this.name = employeePayrollDTO.name;
        this.salary = employeePayrollDTO.salary;
        this.gender = employeePayrollDTO.gender;
        this.startDate = employeePayrollDTO.startDate;
        this.note = employeePayrollDTO.note;
        this.profilePic = employeePayrollDTO.profilePic;
        this.departments = employeePayrollDTO.departments;
    }
}
