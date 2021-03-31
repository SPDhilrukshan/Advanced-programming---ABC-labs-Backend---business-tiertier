package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.enums;

public enum AppointmentStatus {
    REQUESTED("REQUESTED"),
    BILLED("BILLED"),
    CONFIRMED("CONFIRMED"),
    CANCELLED("CANCELLED"),
    DISCHARGED("DISCHARGED");

    public final String appointmentStatus;

    AppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }
}
