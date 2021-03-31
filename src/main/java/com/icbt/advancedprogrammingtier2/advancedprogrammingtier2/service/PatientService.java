package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.service;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.PatientDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {

    public PatientDAO getPatientById(Long patientId);

    public  PatientDAO savePatient(PatientDAO patientDAO);

    public List<PatientDAO> findAll();

    public PatientDAO authorizeLogin(String username, String password);
}
