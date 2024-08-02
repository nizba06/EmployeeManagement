package com.example.employeemanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ElementCollection
    private List<String> skills;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
