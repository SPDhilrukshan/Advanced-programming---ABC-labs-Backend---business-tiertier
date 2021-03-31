package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.Constants;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.FeignConfigure;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.BillingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(url = Constants.TIER1_URL, value = Constants.TIER1_URL , configuration = FeignConfigure.class)
@Component
public interface BillingFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/billing/all-billing/", consumes = "application/json")
    public List<BillingDTO> getAllBilling();

    @RequestMapping(method = RequestMethod.GET, value = "/billing/date-range/start-date/{sDate}/end-date/{eDate}", consumes = "application/json")
    public List<BillingDTO> getBillingsByDateRange(@PathVariable("sDate") String sDate, @PathVariable("eDate") String eDate);

    @RequestMapping(method = RequestMethod.POST, value = "/billing", consumes = "application/json")
    public BillingDTO saveBilling(@RequestBody BillingDTO billingDTO);

}
