package com.example.springemployeepayroll.DTO;

import javax.validation.constraints.*;
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

    @Pattern(regexp="male|female",message = "Gender needs to be either male or female")
    public String gender;

    @JsonFormat(pattern = "dd MMM yyyy")
    @NotNull(message = "Start date cannot be empty")
    @PastOrPresent(message = "startDate could be either today's date or any past date")
    public LocalDate startDate;

    @NotBlank(message= "Note cannot be left blank")
    public String note;

    @NotBlank(message = "ProfilePic cannot be empty")
    public String profilePic;

    @NotNull(message = "Department cannot be left empty")
    public List<String> departments;

    public EmployeePayrollDTO(String name, long salary) {
        this.name = name;
        this.salary = salary;
    }
}
