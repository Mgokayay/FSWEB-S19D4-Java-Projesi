package com.workintech.s19d4.service;

import com.workintech.s19d4.entity.Employee;


import java.util.List;


public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(long id);

    Employee save(Employee employee);

    Employee delete(long id);

    Employee findByEmail(String email);

    List<Employee> findBySalary(double salary);

    List<Employee> findByOrder();
}
