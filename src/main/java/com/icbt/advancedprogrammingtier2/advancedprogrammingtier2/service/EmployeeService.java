package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.service;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.EmpStatusUpdateDAO;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.EmployeeDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    public EmployeeDAO saveEmployee(EmployeeDAO employeeDAO);

    public EmployeeDAO getEmployeeById(Long employeeId);

    public List<EmployeeDAO> getAllEmployees();

    public EmployeeDAO authorizeLogin(String username, String password);

    public EmployeeDAO updateEmployeeDetails(EmployeeDAO employeeDAO);

    public EmployeeDAO updateEmployeeStatus(EmpStatusUpdateDAO employee);

}
