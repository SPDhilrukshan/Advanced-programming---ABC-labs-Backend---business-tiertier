package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.Constants;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.FeignConfigure;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.LabTest;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.PatientDAO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(url = Constants.TIER1_URL, value = Constants.TIER1_URL , configuration = FeignConfigure.class)
@Component
public interface LabTestfeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/lab-test", consumes = "application/json")
    public LabTest saveLabTest(@RequestBody LabTest labTest);

    @RequestMapping(method = RequestMethod.GET, value = "/lab-test/all-labtests", consumes = "application/json")
    public List<LabTest> getAllLabTests();

    @RequestMapping(method = RequestMethod.GET, value = "/lab-test/lab-test/{labTestId}", consumes = "application/json")
    public LabTest getLabTestById(@PathVariable("labTestId") Long labTestId);
}
