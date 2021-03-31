package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.service;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.HashingAlgo;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign.PatientFeignClient;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.Patient;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.PatientDAO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientFeignClient patientFeignClient;

    @Override
    public PatientDAO getPatientById(Long patientId){
        PatientDAO patientDAO = patientFeignClient.getpatientById(patientId);
        return patientDAO;
    }

    @Override
    public PatientDAO savePatient(PatientDAO patientDAO) {

        if(patientDAO.getPatientId() != null){
            PatientDAO patient = patientFeignClient.getpatientById(patientDAO.getPatientId());

            PatientDAO patientToSave = new PatientDAO();
            BeanUtils.copyProperties(patientDAO,patientToSave);
            patientToSave.setPassword(patient.getPassword());
            return patientFeignClient.savePatient(patientToSave);
        }
        patientDAO.setPassword(HashingAlgo.encryptToHash(patientDAO.getPassword()));
        return patientFeignClient.savePatient(patientDAO);
    }


    @Override
    public List<PatientDAO> findAll(){
        return patientFeignClient.getAllPatients();
    }

    @Override
    public PatientDAO authorizeLogin(String username, String password){
        try {
            PatientDAO patientFetched = patientFeignClient.getpatientById(Long.parseLong(username));

            String encryptedPassword = HashingAlgo.encryptToHash(password);

            if(patientFetched.getPassword().equals(encryptedPassword)){
                return patientFetched;
            }
            return null;
        }catch (Exception e){
            return null;
        }

    }
}
