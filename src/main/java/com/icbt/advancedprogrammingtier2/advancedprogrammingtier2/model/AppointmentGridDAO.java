package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentGridDAO {

    private Long appointmentId;
    @JsonFormat(pattern = Constants.dateFormat)
    private Date appointmentDate;
    private String appointmentStatus;
    private String labTestName;
    private Long labTestId;
    private Long patientId;
    private String patientName;
    private String nic;
    private String contactNumber;
}
