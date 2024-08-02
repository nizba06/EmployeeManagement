package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.exception.DepartmentNotFoundException;
import com.example.employeemanagement.exception.EmployeeNotFoundException;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " +id));
    }

    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updateEmployee){
        Employee existingEmployee = getEmployeeById(id);
        existingEmployee.setName(updateEmployee.getName());
        existingEmployee.setSkills(updateEmployee.getSkills());

        if(updateEmployee.getDepartment() != null){
            Department department = departmentRepository.findById(updateEmployee.getDepartment().getId())
                    .orElseThrow(() -> new DepartmentNotFoundException("Department not found with : " + updateEmployee.getDepartment().getId()));
            existingEmployee.setDepartment(department);
        }
        return employeeRepository.save(existingEmployee);
    }
    public void deleteEmployee(Long id){
        if(!employeeRepository.existsById(id)){
            throw new EmployeeNotFoundException("Employee not found with id: " +id);
        }
        employeeRepository.deleteById(id);
    }
}
