package com.example.payroll;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super("employee not found with id : " + id);
    }
}
