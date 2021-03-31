package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.controller;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.*;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.service.AppointmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@CrossOrigin
public class AppointmentController {

    private static Logger logger = LogManager.getLogger(EmployeeController.class);

    @Autowired
    AppointmentService appointmentService;

    @PostMapping()
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment) {

        //no validations, validations in db tier not to release db specifics

        Appointment appointmentSaved = appointmentService.createAppointment(appointment);
        if (appointmentSaved == null) {
            logger.error("Appointment no created");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(appointmentSaved, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(path = "/appointment-id/{appointmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("appointmentId") Long appointmentId ) throws Exception {

        if(appointmentId == null){
            logger.error("Appointment ID is null");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        if(appointment == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(appointment, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(path = "/grid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentGridDAO>> getAppointmentsForGrid() throws Exception {


        List<AppointmentGridDAO> appointmentList = appointmentService.getAppointmentsForGrid();
        if(appointmentList == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(appointmentList, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(path = "/patient-id/{patientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Appointment>> getAppointmentByPatientId(@PathVariable("patientId") Long patientId ) throws Exception {

        if(patientId == null){
            logger.error("patient ID is null");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        List<Appointment> appointmentList = appointmentService.getAllAppointmentsForPatient(patientId);
        if(appointmentList == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(appointmentList, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(path = "/update/appointment-status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appointment> updateAppointmentStatus(@RequestBody AppointmentStatusUpdateDAO appointmentStatusUpdate) throws Exception {

        if(appointmentStatusUpdate.getAppointmentId() == null){
            logger.error("Appointment Id is required");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(appointmentStatusUpdate.getAppointmentStatus().isEmpty()){
            logger.error("Appointment status is required");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Appointment appointmentList = appointmentService.updateAppointmentStatus(appointmentStatusUpdate);
        if(appointmentList == null){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info(appointmentStatusUpdate);
        return new ResponseEntity(appointmentList, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(path = "/date-range/start-date/{sDate}/end-date/{eDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentGridDAO>> getAppointmentByDateRangeFilter(@PathVariable("sDate") String sDate,@PathVariable("eDate") String eDate) throws Exception {

        if(sDate == null || sDate.isEmpty()){
            logger.error("Validation: Start date is required");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(eDate == null || eDate.isEmpty()){
            logger.error("Validation: End date is required");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        List<AppointmentGridDAO> appointmentList = appointmentService.getAppointmentsByDateRange(sDate,eDate);
        if(appointmentList == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(appointmentList, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(path = "/billing", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity billAppointment(@RequestBody BillingDTO billing) throws Exception {

        if(billing.getPatientId() == null){
            logger.error("Validation: patient Id is required");
            return new ResponseEntity<>("Validation: patient Id is required", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(billing.getAppointmentId() == null){
            logger.error("Validation: Appointment Id is required");
            return new ResponseEntity<>("Validation: Appointment Id is required", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(billing.getBilledAmount() == null){
            logger.error("Validation: Billed Amount is required");
            return new ResponseEntity<>("Validation: Billed Amount is required", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(billing.getBilledDate() == null){
            logger.error("Validation: Billed Date is required");
            return new ResponseEntity<>("Validation: Billed Date is required", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        BillingDTO billingSaved = appointmentService.saveBilling(billing);
        if(billingSaved == null){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info(billingSaved);
        return new ResponseEntity(billingSaved, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(path = "/billing-date-range/start-date/{sDate}/end-date/{eDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAppointmentsByDateRange(@PathVariable("sDate") String sDate, @PathVariable("eDate") String eDate) throws Exception {

        if(sDate == null){
            logger.error("Validation: Start date is required");
            return new ResponseEntity<>("Validation: Start date is required", HttpStatus.BAD_REQUEST);
        }

        if(eDate == null){
            logger.error("Validation: End date is required");
            return new ResponseEntity<>("Validation: End date is required", HttpStatus.BAD_REQUEST);
        }

        List<BillingDTO> billingDTOList = appointmentService.getBillingByDateRange(sDate,eDate);
        if(billingDTOList == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(billingDTOList, HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(path = "/all-billing/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllAppointments() throws Exception {

        List<BillingDTO> billingDTOList = appointmentService.getAllBillings();
        if(billingDTOList == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(billingDTOList, HttpStatus.ACCEPTED);
    }
}
