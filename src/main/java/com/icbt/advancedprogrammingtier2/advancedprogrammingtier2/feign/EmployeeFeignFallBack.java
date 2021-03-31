package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.EmployeeDAO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeFeignFallBack implements EmployeeFeignClient{

    @Override
    public EmployeeDAO saveEmployee(EmployeeDAO employee) {
        return null;
    }

    @Override
    public EmployeeDAO getEmployeeById(Long employeeId) {
        return null;
    }

    @Override
    public List<EmployeeDAO> getAllEmployees() {
        return null;
    }

    @Override
    public EmployeeDAO getUserByUsername(String username) {
        return null;
    }
}
