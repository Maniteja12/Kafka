package com.springboot.crudproject.rest;

import com.springboot.crudproject.dao.EmployeeDAO;
import com.springboot.crudproject.entity.Employee;
import com.springboot.crudproject.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    //Implementing with Spring Rest


    private EmployeeService employeeService;
    private EmployeeDAO employeeDAO;

    private EmployeeRestController(EmployeeService theService){
        employeeService = theService;
    }
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) throws RuntimeException {
        Optional<Employee> employee = employeeService.findById(employeeId);
        if(employee.get() == null){
            throw new RuntimeException("Employee id not found "+ employeeId);
        }
        return employee.get();
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmplote(@PathVariable int employeeId){
        Optional<Employee> employee = employeeService.findById(employeeId);
        if(employee.get() == null){
            throw  new RuntimeException("Employee id not found "+ employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted record for employee Id " + employeeId;
    }


}
