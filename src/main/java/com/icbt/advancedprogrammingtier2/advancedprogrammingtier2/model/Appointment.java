package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.Constants;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class Appointment {
    private Long appointmentId;
    @JsonFormat(pattern = Constants.dateFormat)
    private Date appointmentDate;
    private String appointmentStatus;
    private Long labTestId;
    private Long patientId;
}
