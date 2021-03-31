package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.FeignConfigure;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.Constants;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.PatientDAO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = Constants.TIER1_URL, value = Constants.TIER1_URL , configuration = FeignConfigure.class)
@Component
public interface PatientFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/patient/patient-id/{patientId}", consumes = "application/json")
    public PatientDAO getpatientById(@PathVariable("patientId") Long patientId);

    @RequestMapping(method = RequestMethod.POST, value = "/patient", consumes = "application/json")
    public PatientDAO savePatient(@RequestBody PatientDAO patient);

    @RequestMapping(method = RequestMethod.GET, value = "/patient/all-patients/", consumes = "application/json")
    public List<PatientDAO> getAllPatients();
}
