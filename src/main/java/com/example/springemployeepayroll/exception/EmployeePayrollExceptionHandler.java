package com.example.springemployeepayroll.exception;

import com.example.springemployeepayroll.DTO.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
public class EmployeePayrollExceptionHandler{
    private static final String message = "Exception while handling REST request";

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        log.error("Invalid date format", exception);
        ResponseDTO respDTO = new ResponseDTO(message, "Date should be in the dd MMM yyyy format");
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ObjectError> errorObjectList = exception.getBindingResult().getAllErrors();
        List<String> errMsg = errorObjectList.stream()
                            .map(objErr -> objErr.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseDTO respDTO = new ResponseDTO("Exception while processing REST request",errMsg);
        return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmployeePayrollException.class)
    public ResponseEntity<ResponseDTO> handleEmployeePayrollException(EmployeePayrollException exception){
        ResponseDTO respDTO = new ResponseDTO("Exception while processing REST request",exception.getMessage());
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.BAD_REQUEST);

    }
}
