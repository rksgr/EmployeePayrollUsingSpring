package com.example.springemployeepayroll.controllers;

import com.example.springemployeepayroll.DTO.EmployeePayrollDTO;
import com.example.springemployeepayroll.DTO.ResponseDTO;
import com.example.springemployeepayroll.Entity.EmployeePayrollData;
import com.example.springemployeepayroll.service.IEmployeePayrollService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/employeepayrollservice")
@Slf4j
public class EmployeePayrollController {
    private final AtomicLong employeeId = new AtomicLong();

    // Inject the service layer
    @Autowired
    private IEmployeePayrollService iEmployeePayrollService;

    /**
     * Method function: To get the details of ALL the employees from Employee payroll List
     *                  Performs RETRIEVE operation
     *
     * @return A JSON Response containing details of all the employees
     */
    @RequestMapping(value={"","/","/get"})
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(){
        List<EmployeePayrollData> empPayrollDataList = null;
        // Service layer populates the model
        empPayrollDataList = iEmployeePayrollService.getEmployeePayrollData();
        ResponseDTO respDTO = new ResponseDTO("Get call success",empPayrollDataList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    /**
     * Method Function: TO Get Employee Payroll Details based on Employee ID
     *      Performs RETRIEVE operation
     *
     * @param empId
     * @return  JSON Response containing particular employee details
     */
    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") int empId){
        EmployeePayrollData employeePayrollData = null;
        // creating an EmployeePayrollData model object , then populate the response DTO
        employeePayrollData = iEmployeePayrollService.getEmployeePayrollDataById(empId) ;
        ResponseDTO responsDTO = new ResponseDTO("Get call for ID success", employeePayrollData);
        return new ResponseEntity<ResponseDTO>(responsDTO, HttpStatus.OK);
    }

    /**
     * Method Function: TO Get Employee Payroll Details based on department
     *      Performs RETRIEVE operation
     *
     * @param department
     * @return  JSON Response containing particular employee details
     */
    @GetMapping("/department/{department}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("department") String department){
        List<EmployeePayrollData> employeePayrollDataList = null;
        employeePayrollDataList = iEmployeePayrollService.getEmployeesByDepartment(department) ;
        ResponseDTO responsDTO = new ResponseDTO("Get call for department success", employeePayrollDataList);
        return new ResponseEntity<ResponseDTO>(responsDTO, HttpStatus.OK);
    }

    /** UC 1: Add validation to name field so that REST call can be validated */
    /**
     * Method function: To add a new Employee to the Employee Payroll List (CREATE operation)
     *
     * @param employeePayrollDTO
     * @return JSON Response containing employee details including his employee ID
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createEmployeePayrollData(@RequestBody @Valid EmployeePayrollDTO employeePayrollDTO){
        log.debug("Employee DTO: "+ employeePayrollDTO.toString());
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = iEmployeePayrollService.createEmployeePayrollData(employeePayrollDTO);
        ResponseDTO responseDTO = new ResponseDTO("Creation of Employee payroll data successful", employeePayrollData);
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }

    /**
     * Method function: To update the details of a particular Employee, Performs UPDATE operation
     *
     * @param empId
     * @param employeePayrollDTO
     * @return JSON Response containing updated employee details
     */
    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId,
                                                                 @Valid @RequestBody EmployeePayrollDTO employeePayrollDTO){
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = iEmployeePayrollService.updateEmployeePayrollData(empId,employeePayrollDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Employee payroll data  successfully", employeePayrollData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * Method function: To delete the details of a particular Employee given employee Id,
     *                  Performs DELETION operation
     * @param empId
     * @return JSON Response containing details of the employee which have been deleted
     */
    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId){
        iEmployeePayrollService.deleteEmployeePayrollData(empId);
        ResponseDTO respnsDTO = new ResponseDTO("Deleted safaltapoorvak","Deleted Id: "+empId);
        return new ResponseEntity<ResponseDTO>(respnsDTO, HttpStatus.OK);
    }
}