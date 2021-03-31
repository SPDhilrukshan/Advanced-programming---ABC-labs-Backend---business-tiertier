package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.service;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign.LabTestfeignClient;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.LabTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabTestServiceImpl implements LabTestService{

    @Autowired
    LabTestfeignClient labTestfeignClient;

    @Override
    public LabTest saveLabTest(LabTest labTest) {
        return labTestfeignClient.saveLabTest(labTest);
    }

    @Override
    public List<LabTest> getAllLabTests() {
        return labTestfeignClient.getAllLabTests();
    }

    @Override
    public LabTest getLabTestById(Long labTestId){
        return labTestfeignClient.getLabTestById(labTestId);
    }
}
