package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.Appointment;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.AppointmentRequestBodyDAO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class AppointmentFeignFallBack implements AppointmentFeignClient{

    @Override
    public Appointment getAppointmentById(Long appointmentId) {
        return null;
    }

    @Override
    public Appointment saveAppointmentV2(Appointment appointment) {
        return null;
    }

    @Override
    public Appointment saveAppointment(AppointmentRequestBodyDAO appointmentRequestBody) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        return null;
    }

    @Override
    public List<Appointment> getAllAppointments(){
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsByDateRange(String sDate, String eDate){
        return null;
    }
}
