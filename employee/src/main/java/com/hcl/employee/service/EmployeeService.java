package com.hcl.employee.service;

import com.hcl.employee.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Employee getEmployeeById(Long id);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Long empId, Employee employee);

    void deleteEmployee(Long empId);
}
