package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.exception.EmployeeNotFoundException;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private Department department;

    @BeforeEach
    void setUp(){
        department = new Department("Engineering");
        department.setId(1L);

        employee = new Employee();
        employee.setId(1L);
        employee.setName("Sarah");
        employee.setSkills(Arrays.asList("Java", "Spring"));
        employee.setDepartment(department);
    }
    @Test
    void testGetEployeeById_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(employee));

        Employee foundEmployee = employeeService.getEmployeeById(1L);

        Assertions.assertNotNull(foundEmployee);
        assertEquals("Sarah", foundEmployee.getName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetEployeeById_NoFound() {
        when(employeeRepository.findById(3L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getEmployeeById(3L);
        });

        assertEquals("Employee not found with id: 3", exception.getMessage());
        verify(employeeRepository, times(1)).findById(3L);
    }
}
