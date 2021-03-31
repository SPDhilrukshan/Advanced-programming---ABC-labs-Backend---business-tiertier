package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.controller;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.PatientDAO;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.service.PatientService;
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
@RequestMapping("/patient")
@CrossOrigin
public class PatientController {

    private static Logger logger = LogManager.getLogger(PatientController.class);

    @Autowired
    PatientService patientService;

   @PostMapping()
    public ResponseEntity<PatientDAO> create(@RequestBody PatientDAO patientDAO) {

        //no validations, validations in db tier not to release db specifics

       PatientDAO patientDAOSaved = patientService.savePatient(patientDAO);
        if (patientDAOSaved == null) {
            logger.error("Patient not saved");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(patientDAOSaved, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/patient-id-tier2/{patientId}")
    public ResponseEntity<PatientDAO> getPatientByPatientId(@PathVariable("patientId") Long patientId) throws ParseException {

        if (patientId == null || patientId.equals("null")) {
            return new ResponseEntity("Validation Error - patientId Id is required", HttpStatus.BAD_REQUEST);
        }

        PatientDAO patientDAO = patientService.getPatientById(patientId);

        if (patientDAO == null) {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(patientDAO, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(path = "/all-patients/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientDAO>> getAllPatients() throws Exception {

        List<PatientDAO> patients = patientService.findAll();
        if(patients == null || patients.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(patients, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(path = "/authorize/username/{username}/password/{password}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity authorizePatientLogin(@PathVariable("username") String username,
                                                @PathVariable("password") String password) throws Exception {

        PatientDAO patient = patientService.authorizeLogin(username,password);
        if(patient == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(patient, HttpStatus.ACCEPTED);
    }

}
