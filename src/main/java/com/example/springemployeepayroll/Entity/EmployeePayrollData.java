package com.example.springemployeepayroll.Entity;

import com.example.springemployeepayroll.DTO.EmployeePayrollDTO;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.persistence.JoinColumn;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;

@Entity
@Table(name="employee_payroll")
public @Data class EmployeePayrollData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="employee_id")
    private int employeeId;

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$", message = "Employee name invalid")
    @Column(name="name")
    private String name;

    @Min(value=5000,message="Salary cannot be less than 5000")
    @Column()
    private long salary;

    private String gender;
    private LocalDate startDate;
    private String note;
    private String profilePic;

    // Another table created since one to many relationship is there
    @ElementCollection
    @CollectionTable(name="employee_department", joinColumns=@JoinColumn(name="id"))
    @Column(name="department")
    private List<String> departments;


    public EmployeePayrollData(){}

    public EmployeePayrollData(EmployeePayrollDTO employeePayrollDTO) {
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
