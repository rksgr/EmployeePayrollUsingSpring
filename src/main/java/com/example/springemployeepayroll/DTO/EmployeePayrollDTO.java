package com.example.springemployeepayroll.DTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;
import java.util.regex.*;

public @ToString class EmployeePayrollDTO {

    @Pattern(regexp = "^[A-Z]{1,}[a-zA-Z]{2,}$", message = "Employee name invalid")
    @NotEmpty(message="Cannot be left blank")
    public String name;

    @Min(value=5000,message="Salary cannot be less than 5000")
    public long salary;
    public String gender;

    @JsonFormat(pattern = "dd MMM yyyy")
    public LocalDate startDate;
    public String note;
    public String profilePic;
    public List<String> departments;

    public EmployeePayrollDTO(String name, long salary) {
        this.name = name;
        this.salary = salary;
    }
}
