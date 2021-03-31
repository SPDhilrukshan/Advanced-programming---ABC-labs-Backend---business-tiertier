package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.service;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.LabTest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LabTestService {

    public LabTest saveLabTest(LabTest labTest);

    public List<LabTest> getAllLabTests();

    public LabTest getLabTestById(Long labTestId);
}
