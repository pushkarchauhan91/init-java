package com.company.map.easy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class PrintMapTest {

    private PrintMap printMap;
    private Map<Integer, Employee> employeeMap;

    @BeforeEach
    void setUp() {

        printMap = new PrintMap();

        Employee employee1 = createEmployee1();
        Employee employee2 = createEmployee2();

        employeeMap = new HashMap<>();

        employeeMap.put(employee1.getId(), employee1);
        employeeMap.put(employee2.getId(), employee2);
    }

    @Test
    void testPrintUsingMapForEach() {

        printMap.printUsingMapForEach(employeeMap);
    }

    @Test
    void testPrintUsingEntrySet() {

        printMap.printUsingEntrySet(employeeMap);
    }

    @Test
    void testPrintUsingEntrySetForEach() {

        printMap.printUsingEntrySetForEach(employeeMap);
    }

    @Test
    void testPrintUsingKeySet() {

        printMap.printUsingKeySet(employeeMap);
    }

    @Test
    void testPrintUsingValues() {

        printMap.printUsingValues(employeeMap);
    }

    @Test
    void testPrintUsingIterator() {

        printMap.printUsingIterator(employeeMap);
    }

    @Test
    void testPrintUsingStream() {

        printMap.printUsingStream(employeeMap);
    }

    @Test
    void testPrintMapDirectly() {

        printMap.printMapDirectly(employeeMap);
    }

    private Employee createEmployee1() {

        Employee employee = new Employee();

        employee.setId(1);
        employee.setName("John Doe");
        employee.setSalary(50000.0);

        Department department = new Department();

        department.setId(101);
        department.setName("IT");
        department.setLocation("New York");

        employee.setDepartment(department);

        return employee;
    }

    private Employee createEmployee2() {

        Employee employee = new Employee();

        employee.setId(2);
        employee.setName("Jane Smith");
        employee.setSalary(60000.0);

        Department department = new Department();

        department.setId(102);
        department.setName("HR");
        department.setLocation("Los Angeles");

        employee.setDepartment(department);

        return employee;
    }
}