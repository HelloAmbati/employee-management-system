package com.hcl.employee.service.impl;

import com.hcl.employee.model.Employee;
import com.hcl.employee.repository.EmployeeRepository;
import com.hcl.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public  Employee getEmployeeById(Long empId){
        return employeeRepository.findById(empId)
                .orElseThrow(()->new RuntimeException("Employee not found with Id" +empId));
    }

    @Override
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Long empId, Employee employee){
       Employee existing = getEmployeeById(empId);
       existing.setEmpName(employee.getEmpName());
       existing.setEmpSalary(employee.getEmpSalary());
       existing.setEmpAge(employee.getEmpAge());
       return employeeRepository.save(existing);
    }

    @Override
    public void deleteEmployee(Long empId){
        employeeRepository.deleteById(empId);
    }


}
