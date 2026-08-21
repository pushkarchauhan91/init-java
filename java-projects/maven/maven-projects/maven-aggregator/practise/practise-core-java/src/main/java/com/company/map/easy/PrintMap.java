package com.company.map.easy;

import java.util.Iterator;
import java.util.Map;

public class PrintMap {

    public void printUsingMapForEach(Map<Integer, Employee> employeeMap) {

//        employeeMap.forEach((id, employee) -> {
//            printEmployee(id, employee);
//        });

        employeeMap.forEach(this::printEmployee);
    }

    public void printUsingEntrySet(Map<Integer, Employee> employeeMap) {

        for (Map.Entry<Integer, Employee> entry : employeeMap.entrySet()) {

            Integer id = entry.getKey();
            Employee employee = entry.getValue();

            printEmployee(id, employee);
        }
    }

    public void printUsingEntrySetForEach(Map<Integer, Employee> employeeMap) {

        employeeMap.entrySet().forEach(entry -> {

            Integer id = entry.getKey();
            Employee employee = entry.getValue();

            printEmployee(id, employee);
        });
    }

    public void printUsingKeySet(Map<Integer, Employee> employeeMap) {

        for (Integer id : employeeMap.keySet()) {

            Employee employee = employeeMap.get(id);

            printEmployee(id, employee);
        }
    }

    public void printUsingValues(Map<Integer, Employee> employeeMap) {

        for (Employee employee : employeeMap.values()) {

            printEmployee(employee.getId(), employee);
        }
    }

    public void printUsingIterator(Map<Integer, Employee> employeeMap) {

        Iterator<Map.Entry<Integer, Employee>> iterator =
                employeeMap.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<Integer, Employee> entry = iterator.next();

            Integer id = entry.getKey();
            Employee employee = entry.getValue();

            printEmployee(id, employee);
        }
    }

    public void printUsingStream(Map<Integer, Employee> employeeMap) {

        employeeMap.entrySet()
                .stream()
                .forEach(entry -> {

                    Integer id = entry.getKey();
                    Employee employee = entry.getValue();

                    printEmployee(id, employee);
                });
    }

    public void printMapDirectly(Map<Integer, Employee> employeeMap) {

        System.out.println(employeeMap);
    }

    private void printEmployee(Integer id, Employee employee) {

        System.out.println("Employee ID: " + id);
        System.out.println("Name: " + employee.getName());
        System.out.println("Salary: " + employee.getSalary());
        System.out.println(
                "Department: " + employee.getDepartment().getName());
        System.out.println(
                "Location: " + employee.getDepartment().getLocation());
        System.out.println();
    }
}