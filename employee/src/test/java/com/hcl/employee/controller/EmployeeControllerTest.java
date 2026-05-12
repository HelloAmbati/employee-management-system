package com.hcl.employee.controller;

import com.hcl.employee.model.Employee;
import com.hcl.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void testCreateEmployee() {

        Employee employee = new Employee();
        employee.setEmpId(1L);
        employee.setEmpName("John");

        when(employeeService.saveEmployee(employee)).thenReturn(employee);

        Employee result = employeeController.createEmployee(employee);

        assertNotNull(result);
        assertEquals(1L, result.getEmpId());
        assertEquals("John", result.getEmpName());

        verify(employeeService, times(1)).saveEmployee(employee);
    }

    @Test
    void testGetEmployee() {

        Employee employee = new Employee();
        employee.setEmpId(1L);
        employee.setEmpName("John");

        when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        Employee result = employeeController.getEmployee(1L);

        assertNotNull(result);
        assertEquals(1L, result.getEmpId());
        assertEquals("John", result.getEmpName());

        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    void testGetAllEmployees() {

        Employee employee1 = new Employee();
        employee1.setEmpId(1L);
        employee1.setEmpName("John");

        Employee employee2 = new Employee();
        employee2.setEmpId(2L);
        employee2.setEmpName("David");

        List<Employee> employeeList = Arrays.asList(employee1, employee2);

        when(employeeService.getAllEmployees()).thenReturn(employeeList);

        List<Employee> result = employeeController.getAllEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getEmpName());
        assertEquals("David", result.get(1).getEmpName());

        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void testUpdateEmployee() {

        Employee employee = new Employee();
        employee.setEmpId(1L);
        employee.setEmpName("Updated John");

        when(employeeService.updateEmployee(1L, employee)).thenReturn(employee);

        Employee result = employeeController.updateEmployee(1L, employee);

        assertNotNull(result);
        assertEquals(1L, result.getEmpId());
        assertEquals("Updated John", result.getEmpName());

        verify(employeeService, times(1))
                .updateEmployee(1L, employee);
    }

    @Test
    void testDeleteEmployee() {

        doNothing().when(employeeService).deleteEmployee(1L);

        String result = employeeController.deleteEmployee(1L);

        assertNotNull(result);
        assertEquals("Employee deleted successfully", result);

        verify(employeeService, times(1)).deleteEmployee(1L);
    }
}