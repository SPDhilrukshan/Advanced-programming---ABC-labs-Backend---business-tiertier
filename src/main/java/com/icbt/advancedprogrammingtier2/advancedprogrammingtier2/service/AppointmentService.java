package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.service;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {

    public Appointment getAppointmentById(Long appointmentId);

    //public Appointment createAppointment(AppointmentRequestBodyDAO appointmentRequestBody);

    public Appointment createAppointment(Appointment appointment);

    public List<Appointment> getAllAppointmentsForPatient(Long patientId);

    public List<AppointmentGridDAO> getAppointmentsForGrid();

    public Appointment updateAppointmentStatus(AppointmentStatusUpdateDAO appointmentStatusUpdateDAO);

    public List<AppointmentGridDAO> getAppointmentsByDateRange(String startDate, String endDate);

    public BillingDTO saveBilling(BillingDTO billing);

    public List<BillingDTO> getBillingByDateRange(String startDate, String endDate);

    public List<BillingDTO> getAllBillings();
}
