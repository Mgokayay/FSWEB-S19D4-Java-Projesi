package com.workintech.s19d4.repository;

import com.workintech.s19d4.entity.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
 class EmployeeRepositoryTest {
    //test edeceğimiz şeyi inject ediyoruz

    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @BeforeEach
    void setUp() {
        Employee employee1=new Employee();
        employee1.setFirstName("Ali");
        employee1.setLastName("Veli");
        employee1.setEmail("ali@veli.com");
        employee1.setSalary(30000);

        Employee employee2=new Employee();
        employee2.setFirstName("Ahmet");
        employee2.setLastName("Mehmet");
        employee2.setEmail("ahmet@mehmet.com");
        employee2.setSalary(45000);

        Employee employee3=new Employee();
        employee3.setFirstName("employee3");
        employee3.setLastName("employee3");
        employee3.setEmail("employee3@employee3.com");
        employee3.setSalary(450000);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employeeRepository.saveAll(employees);
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @Test
    void findByEmail() {

        String nonExistingEmail = "gokay@gokay.com";
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(nonExistingEmail);
        assertEquals(Optional.empty(),employeeOptional);

        String existingEmail = "ali@veli.com";
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(existingEmail);
        assertNotNull(existingEmployee);
        assertEquals("Ali",existingEmployee.get().getFirstName());
        assertEquals("Veli",existingEmployee.get().getLastName());

    }

    @Test
    void findBySalary() {
        List<Employee> employees = employeeRepository.findBySalary(35000);
        assertEquals(2,employees.size());
        assertEquals("employee3",employees.get(0).getFirstName());
    }

    @Test
    void findByOrder(){
        List<Employee> employees = employeeRepository.findByOrder();
        assertEquals(3,employees.size());
        assertEquals("employees3",employees.get(0).getFirstName());
        assertEquals("Ahmet",employees.get(1).getFirstName());
        assertEquals("Ali",employees.get(2).getFirstName());
    }
}
