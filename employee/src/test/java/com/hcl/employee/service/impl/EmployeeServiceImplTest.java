package com.hcl.employee.service.impl;

import com.hcl.employee.model.Employee;
import com.hcl.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void testSaveEmployee() {

        Employee employee = new Employee();
        employee.setEmpName("John");
        employee.setEmpAge(25);
        employee.setEmpSalary(50000.0);

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.saveEmployee(employee);

        assertNotNull(result);
        assertEquals("John", result.getEmpName());
        assertEquals(25, result.getEmpAge());
        assertEquals(50000.0, result.getEmpSalary());

        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testGetEmployeeById() {

        Employee employee = new Employee();
        employee.setEmpId(1L);
        employee.setEmpName("John");

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getEmpId());
        assertEquals("John", result.getEmpName());

        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetEmployeeByIdThrowsException() {

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> employeeService.getEmployeeById(1L)
        );

        assertEquals("Employee not found with Id1", exception.getMessage());

        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllEmployees() {

        Employee employee1 = new Employee();
        employee1.setEmpId(1L);
        employee1.setEmpName("John");

        Employee employee2 = new Employee();
        employee2.setEmpId(2L);
        employee2.setEmpName("David");

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getEmpName());
        assertEquals("David", result.get(1).getEmpName());

        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testUpdateEmployee() {

        Employee existingEmployee = new Employee();
        existingEmployee.setEmpId(1L);
        existingEmployee.setEmpName("Old Name");
        existingEmployee.setEmpAge(22);
        existingEmployee.setEmpSalary(30000.0);

        Employee updatedEmployee = new Employee();
        updatedEmployee.setEmpName("New Name");
        updatedEmployee.setEmpAge(28);
        updatedEmployee.setEmpSalary(70000.0);

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(existingEmployee));

        when(employeeRepository.save(existingEmployee))
                .thenReturn(existingEmployee);

        Employee result = employeeService.updateEmployee(1L, updatedEmployee);

        assertNotNull(result);
        assertEquals("New Name", result.getEmpName());
        assertEquals(28, result.getEmpAge());
        assertEquals(70000.0, result.getEmpSalary());

        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(existingEmployee);
    }

    @Test
    void testDeleteEmployee() {

        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }
}