package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.Constants;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.FeignConfigure;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.Appointment;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.AppointmentRequestBodyDAO;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.PatientDAO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@FeignClient(url = Constants.TIER1_URL, value = Constants.TIER1_URL , configuration = FeignConfigure.class)
@Component
public interface AppointmentFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/appointment/appointment-id/{appointmentId}", consumes = "application/json")
    public Appointment getAppointmentById(@PathVariable("appointmentId") Long appointmentId);

    @RequestMapping(method = RequestMethod.POST, value = "/appointment", consumes = "application/json")
    public Appointment saveAppointment(@RequestBody AppointmentRequestBodyDAO appointmentRequestBody);

    @RequestMapping(method = RequestMethod.POST, value = "/appointment", consumes = "application/json")
    public Appointment saveAppointmentV2(@RequestBody Appointment appointment);

    @RequestMapping(method = RequestMethod.GET, value = "/appointment/appointment-id/patient-id/{patientId}", consumes = "application/json")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable("patientId") Long patientId);

    @RequestMapping(method = RequestMethod.GET, value = "/appointment/all-appointments/", consumes = "application/json")
    public List<Appointment> getAllAppointments();

    @RequestMapping(method = RequestMethod.GET, value = "/appointment/date-range/start-date/{sDate}/end-date/{eDate}", consumes = "application/json")
    public List<Appointment> getAppointmentsByDateRange(@PathVariable("sDate") String sDate, @PathVariable("eDate") String eDate);

}
