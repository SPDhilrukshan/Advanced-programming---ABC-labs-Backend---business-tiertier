package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillingDTO {
    private Long billingId;
    private Date billedDate;
    private Long appointmentId;
    private Long patientId;
    private Long billedAmount;
    private String billingType;
}
