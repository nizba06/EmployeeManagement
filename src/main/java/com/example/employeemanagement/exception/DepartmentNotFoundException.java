package com.example.employeemanagement.exception;

import com.example.employeemanagement.entity.Department;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(String message){
        super(message);
    }
}
