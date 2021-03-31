package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.PatientDAO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class PatientFeignFallBack implements PatientFeignClient {

    @Override
    public PatientDAO getpatientById(Long patientId){
        return null;
    }

    @Override
    public PatientDAO savePatient(@RequestBody PatientDAO patient){
        return null;
    }

    @Override
    public List<PatientDAO> getAllPatients(){
        return null;
    }
}
