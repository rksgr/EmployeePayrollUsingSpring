package com.example.springemployeepayroll.repository;

import com.example.springemployeepayroll.Entity.EmployeePayrollData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface EmployeePayrollRepository extends JpaRepository<EmployeePayrollData, Integer> {

}
