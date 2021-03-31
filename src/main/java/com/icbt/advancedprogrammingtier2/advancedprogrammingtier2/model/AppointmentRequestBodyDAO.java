package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentRequestBodyDAO {
    private Appointment appointment;
    private Long patientId;
}
