package com.example.springemployeepayroll.controllers;

import com.example.springemployeepayroll.DTO.EmployeePayrollDTO;
import com.example.springemployeepayroll.DTO.ResponseDTO;
import com.example.springemployeepayroll.Entity.EmployeePayrollData;
//import com.example.springemployeepayroll.service.EmployeePayrollService;
import com.example.springemployeepayroll.service.IEmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    // Inject the service layer
    @Autowired
    private IEmployeePayrollService iEmployeePayrollService;

    @RequestMapping(value={"","/","/get"})
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(){
        List<EmployeePayrollData> empPayrollDataList = null;
        // Service layer populates the model
        empPayrollDataList = iEmployeePayrollService.getEmployeePayrollData();
        ResponseDTO respDTO = new ResponseDTO("Get call success",empPayrollDataList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") int empId){
        EmployeePayrollData employeePayrollData = null;
        // creating an EmployeePayrollData model object , then populate the response DTO
        employeePayrollData = iEmployeePayrollService.getEmployeePayrollDataById(empId) ;
        ResponseDTO responsDTO = new ResponseDTO("Get call for ID success", employeePayrollData);
        return new ResponseEntity<ResponseDTO>(responsDTO, HttpStatus.OK);
    }

    /** UC 1: Add validation to name field so that REST call can be validated */
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addEmployeePayrollData(@RequestBody EmployeePayrollDTO employeePayrollDTO){
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = iEmployeePayrollService.createEmployeePayrollData(employeePayrollDTO);
        // Model object is being populated in the controller. This should ideally be done in the service layer.
        ResponseDTO responseDTO = new ResponseDTO("Creation of Employee payroll data successful", employeePayrollData);
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId,
                                                                 @Valid @RequestBody EmployeePayrollDTO employeePayrollDTO){
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = iEmployeePayrollService.updateEmployeePayrollData(empId,employeePayrollDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Employee payroll data  successfully", employeePayrollData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId){
        iEmployeePayrollService.deleteEmployeePayrollData(empId);
        ResponseDTO respnsDTO = new ResponseDTO("Deleted safaltapoorvak","Deleted Id: "+empId);
        return new ResponseEntity<ResponseDTO>(respnsDTO, HttpStatus.OK);
    }
}