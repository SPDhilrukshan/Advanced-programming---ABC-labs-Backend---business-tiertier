package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.service;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.Constants;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.enums.AppointmentStatus;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign.AppointmentFeignClient;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign.BillingFeignClient;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign.LabTestfeignClient;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign.PatientFeignClient;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    AppointmentFeignClient appointmentFeignClient;

    @Autowired
    PatientFeignClient patientFeignClient;

    @Autowired
    LabTestfeignClient labTestfeignClient;

    @Autowired
    BillingFeignClient billingFeignClient;

    @Override
    public Appointment getAppointmentById(Long appointmentId) {
        return appointmentFeignClient.getAppointmentById(appointmentId);
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
//        Long patientId = appointmentRequestBody.getPatientId();
//        AppointmentDAO appointment = appointmentRequestBody.getAppointment();
//
//        PatientDAO patient = patientFeignClient.getpatientById(patientId);
//
//       appointment.setPatient(patient);
//        List<AppointmentDAO> appointmentList = patient.getAppointmentList();
//        appointment.setPatient(patient);
//        appointmentList.add(appointment);
//
//        patient.setAppointmentList(appointmentList);
//        PatientDAO patientUpdated = patientFeignClient.savePatient(patient);
//
//        return appointment;
            return appointmentFeignClient.saveAppointmentV2(appointment);
    }

    @Override
    public List<Appointment> getAllAppointmentsForPatient(Long patientId) {
        return appointmentFeignClient.getAppointmentsByPatientId(patientId);
    }

    @Override
    public List<AppointmentGridDAO> getAppointmentsForGrid(){

        List<AppointmentGridDAO> appointmentGridDAOList = new ArrayList<>();
        List<Appointment> appointmentList = appointmentFeignClient.getAllAppointments();

        return copyProperties(appointmentList);
    }

    @Override
    public Appointment updateAppointmentStatus(AppointmentStatusUpdateDAO appointmentStatusUpdateDAO){

        Appointment appointmentFetched = appointmentFeignClient.getAppointmentById(appointmentStatusUpdateDAO.getAppointmentId());

        if(appointmentFetched == null){
            return null;
        }

        appointmentFetched.setAppointmentStatus(appointmentStatusUpdateDAO.getAppointmentStatus());

        return appointmentFeignClient.saveAppointmentV2(appointmentFetched);


    }

    @Override
    public List<AppointmentGridDAO> getAppointmentsByDateRange(String startDate, String endDate){

        List<Appointment> appointmentList = appointmentFeignClient.getAppointmentsByDateRange(startDate,endDate);
        return copyProperties(appointmentList);
    }

    public List<AppointmentGridDAO> copyProperties(List<Appointment> appointmentList){

        List<AppointmentGridDAO> appointmentGridDAOList = new ArrayList<>();
        for(Appointment appointment: appointmentList){
            AppointmentGridDAO appointmentGridDAO = new AppointmentGridDAO();

            appointmentGridDAO.setAppointmentDate(appointment.getAppointmentDate());
            appointmentGridDAO.setAppointmentId(appointment.getAppointmentId());
            appointmentGridDAO.setAppointmentStatus(appointment.getAppointmentStatus());
            appointmentGridDAO.setLabTestId(appointment.getLabTestId());
            appointmentGridDAO.setPatientId(appointment.getPatientId());

            PatientDAO patientDAO = patientFeignClient.getpatientById(appointment.getPatientId());

            if(patientDAO != null){
                appointmentGridDAO.setPatientName(patientDAO.getFirstName() + " " + patientDAO.getLastName());
                appointmentGridDAO.setContactNumber(patientDAO.getContactNumber());
                appointmentGridDAO.setNic(patientDAO.getNIC());
            }

            LabTest labTest = labTestfeignClient.getLabTestById(appointment.getLabTestId());

            if(labTest != null){
                appointmentGridDAO.setLabTestName(labTest.getLabTestName());
            }

            appointmentGridDAOList.add(appointmentGridDAO);
        }
        return appointmentGridDAOList;
    }

    @Override
    public BillingDTO saveBilling(BillingDTO billing){

        BillingDTO billingDTO = billingFeignClient.saveBilling(billing);

        if(billingDTO == null){
            return null;
        }

        AppointmentStatusUpdateDAO appointmentStatusUpdate = new AppointmentStatusUpdateDAO();
        appointmentStatusUpdate.setAppointmentId(billing.getAppointmentId());
        appointmentStatusUpdate.setAppointmentStatus(AppointmentStatus.BILLED.getAppointmentStatus());
        updateAppointmentStatus(appointmentStatusUpdate);

        return billingDTO;
    }

    @Override
    public List<BillingDTO> getBillingByDateRange(String startDate, String endDate){

        return billingFeignClient.getBillingsByDateRange(startDate, endDate);

    }

    @Override
    public List<BillingDTO> getAllBillings(){

        return billingFeignClient.getAllBilling();
    }
}
