package com.example.springemployeepayroll;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class SpringEmployeePayrollApplication {

    /**UC4 : Database setting as Environment Variable **/
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringEmployeePayrollApplication.class, args);
        // Logging the environment from appropriate application property
        log.info("Employee Payroll App started in {} environment",context.getEnvironment().getProperty("environment"));

        // Log the data source UserName
        log.info("Employee payroll DB user is {}",context.getEnvironment().getProperty("spring.datasource.username"));
    }
}
