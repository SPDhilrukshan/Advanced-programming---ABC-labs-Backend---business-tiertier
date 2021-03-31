package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.controller;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.LabTest;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.PatientDAO;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.service.LabTestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lab-test")
@CrossOrigin
public class LabTestController {

    private static Logger logger = LogManager.getLogger(PatientController.class);

    @Autowired
    LabTestService labTestService;

    @PostMapping()
    public ResponseEntity<LabTest> create(@RequestBody LabTest labTest) {

        //no validations, validations in db tier not to release db specifics

        LabTest labTestSaved = labTestService.saveLabTest(labTest);
        if (labTestSaved == null) {
            logger.error("Lab test not saved");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(labTestSaved, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(path = "/all-lab-tests/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LabTest>> getAllLabTests() throws Exception {

        List<LabTest> labTests = labTestService.getAllLabTests();
        if(labTests == null || labTests.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(labTests, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(path = "/lab-test/{labTestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getLabTestById(@PathVariable("labTestId") Long labTestId) throws Exception {

        if(labTestId == null){
            logger.error("Validation: LabTest ID is required");
            return new ResponseEntity<>("Validation: LabTest ID is required", HttpStatus.BAD_REQUEST);
        }

        LabTest labTest = labTestService.getLabTestById(labTestId);

        if(labTest == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(labTest, HttpStatus.ACCEPTED);
    }
}
