package com.workintech.s19d4.controller;

import com.workintech.s19d4.entity.Employee;
import com.workintech.s19d4.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee save(@RequestBody Employee employee){
        return employeeService.save(employee);
    }
    @GetMapping("/order")
    public List<Employee> findByOrder(){
        return employeeService.findByOrder();
    }
}
