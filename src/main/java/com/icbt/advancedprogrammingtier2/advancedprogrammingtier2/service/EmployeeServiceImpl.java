package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.service;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.Constants;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.HashingAlgo;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign.EmployeeFeignClient;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign.PatientFeignClient;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.EmpStatusUpdateDAO;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.EmployeeDAO;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.Patient;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.PatientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeFeignClient employeeFeignClient;

    @Override
    public EmployeeDAO saveEmployee(EmployeeDAO employeeDAO) {

        employeeDAO.setPassword(HashingAlgo.encryptToHash(employeeDAO.getPassword()));

        //check whether username is unique
        List<EmployeeDAO> employeeList = employeeFeignClient.getAllEmployees();

        if(!employeeList.isEmpty()){
            List<String> alreadySavedUsernames = employeeList.stream().map(EmployeeDAO::getUserName).collect(Collectors.toList());
             if(alreadySavedUsernames.contains(employeeDAO.getUserName())){
                 return new EmployeeDAO();
             }
        }
        return employeeFeignClient.saveEmployee(employeeDAO);


    }

    @Override
    public EmployeeDAO getEmployeeById(Long employeeId) {

        return employeeFeignClient.getEmployeeById(employeeId);
    }

    @Override
    public List<EmployeeDAO> getAllEmployees() {
        return employeeFeignClient.getAllEmployees();
    }

    @Override
    public EmployeeDAO authorizeLogin(String username,String password){
        EmployeeDAO employee = employeeFeignClient.getUserByUsername(username);

        if(employee == null || employee.getUserName().isEmpty()){
            return null;
        }

        String encryptedPassword = HashingAlgo.encryptToHash(password);

        if(encryptedPassword.equals(employee.getPassword()) && employee.getEmployeeAcceptStatus().equalsIgnoreCase("ACCEPTED")){
            return employee;
        }

        return null;

    }

    @Override
    public EmployeeDAO updateEmployeeDetails(EmployeeDAO employee){

        if(employee.getUserName().isEmpty()){
            return null;
        }

        if(employee.getUserId() == null){
            return null;
        }

        EmployeeDAO employeeSavedEarlier = employeeFeignClient.getEmployeeById(employee.getUserId());

        if(employeeSavedEarlier.getUserId() == null){
            return null;
        }

        employeeSavedEarlier.setAddress(employee.getAddress());
        employeeSavedEarlier.setContactNumber(employee.getContactNumber());
        employeeSavedEarlier.setDateOfBirth(employee.getDateOfBirth());
        employeeSavedEarlier.setEmail(employee.getEmail());
        employeeSavedEarlier.setFirstName(employee.getFirstName());
        employeeSavedEarlier.setLastName(employee.getLastName());
        employeeSavedEarlier.setGender(employee.getGender());
        employeeSavedEarlier.setMaritalStatus(employee.getMaritalStatus());
        employeeSavedEarlier.setNationality(employee.getNationality());
        employeeSavedEarlier.setNIC(employee.getNIC());
        employeeSavedEarlier.setStaffType(employee.getStaffType());

        return employeeFeignClient.saveEmployee(employeeSavedEarlier);
    }

    @Override
    public EmployeeDAO updateEmployeeStatus(EmpStatusUpdateDAO employee){

        EmployeeDAO employeeFetched = employeeFeignClient.getEmployeeById(employee.getEmployeeId());

        if(employeeFetched == null){
            return null;
        }

        employeeFetched.setEmployeeAcceptStatus(employee.getEmployeeStatus());

        return employeeFeignClient.saveEmployee(employeeFetched);
    }
}
