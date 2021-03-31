package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.controller;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.EmpStatusUpdateDAO;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.EmployeeDAO;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {

    private static Logger logger = LogManager.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity create(@RequestBody EmployeeDAO employeeDAO) {

        //no validations, validations in db tier not to release db specifics

        EmployeeDAO employeeDAOSaved = employeeService.saveEmployee(employeeDAO);
        if (employeeDAOSaved == null) {
            logger.error("Employee Not saved");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if( employeeDAOSaved.getUserName().isEmpty()){
            logger.error("Employee username not unique");
            return new ResponseEntity<>("Username is not unique", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeeDAOSaved, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/employee-id-tier2/{employeeId}")
    public ResponseEntity<EmployeeDAO> getEmployeeById(@PathVariable("employeeId") Long employeeId) throws ParseException {

        if (employeeId == null || employeeId.equals("null")) {
            return new ResponseEntity("Validation Error - patientId Id is required", HttpStatus.BAD_REQUEST);
        }

        EmployeeDAO employee = employeeService.getEmployeeById(employeeId);

        if (employee == null) {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employee, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(path = "/all-employees/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDAO>> getAllEmployees() throws Exception {

        List<EmployeeDAO> employees = employeeService.getAllEmployees();
        if(employees == null || employees.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(employees, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(path = "/authorize-login/username/{username}/password/{password}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDAO> authorizeLogin(@PathVariable("username") String username,
                                                      @PathVariable("password") String password) throws Exception {

        EmployeeDAO employee = employeeService.authorizeLogin(username,password);
        if(employee == null || employee.getUserName().isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(employee, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(path = "/update/", method = RequestMethod.POST,produces =MediaType.APPLICATION_JSON_VALUE ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateEmployee(@RequestBody EmployeeDAO employee) throws Exception {

        EmployeeDAO employeeUpdated = employeeService.updateEmployeeDetails(employee);
        if(employeeUpdated == null){
            return new ResponseEntity("Error while updating",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(employeeUpdated, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(path = "/update/status", method = RequestMethod.POST,produces =MediaType.APPLICATION_JSON_VALUE ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateEmployeeStatus(@RequestBody EmpStatusUpdateDAO employee) throws Exception {

        if(employee.getEmployeeId() == null){
            logger.error("Validation: employee id required to update status");
            return new ResponseEntity<>("Validation: employee id required to update status", HttpStatus.BAD_REQUEST);
        }

        if(employee.getEmployeeStatus().isEmpty()){
            logger.error("Validation: employee status required to update status");
            return new ResponseEntity<>("Validation: employee status required to update status", HttpStatus.BAD_REQUEST);
        }

        EmployeeDAO employeeUpdated = employeeService.updateEmployeeStatus(employee);
        if(employeeUpdated == null){
            return new ResponseEntity("Error while updating Status",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(employeeUpdated, HttpStatus.ACCEPTED);
    }
}
