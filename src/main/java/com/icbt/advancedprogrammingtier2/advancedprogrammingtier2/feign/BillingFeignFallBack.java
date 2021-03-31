package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.feign;

import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model.BillingDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BillingFeignFallBack implements BillingFeignClient {

    @Override
    public List<BillingDTO> getAllBilling() {
        return null;
    }

    @Override
    public List<BillingDTO> getBillingsByDateRange(String sDate, String eDate) {
        return null;
    }

    @Override
    public BillingDTO saveBilling(BillingDTO billingDTO) {
        return null;
    }
}
