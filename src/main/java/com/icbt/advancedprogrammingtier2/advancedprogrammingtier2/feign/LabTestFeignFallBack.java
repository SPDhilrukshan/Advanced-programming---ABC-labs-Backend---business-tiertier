package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.LabTest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
public class LabTestFeignFallBack implements LabTestfeignClient{

    @Override
    public LabTest saveLabTest(LabTest labTest) {
        return null;
    }

    @Override
    public List<LabTest> getAllLabTests() {
        return null;
    }

    @Override
    public LabTest getLabTestById(Long labTestId){
        return null;
    }
}
