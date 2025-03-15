package com.springboot.crudproject.service;

import com.springboot.crudproject.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();
    Optional<Employee> findById(Integer id);
    Employee save(Employee employee);
    void deleteById(Integer id);
}
