package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config.Constants;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeDAO {
    private Long userId;
    private String firstName;
    private String lastName;

    @JsonFormat(pattern = Constants.dateFormat)
    private Date dateOfBirth;
    private String email;
    private String contactNumber;
    private String NIC;
    private String address;
    private String gender;
    private String maritalStatus;
    private String nationality;
    private String staffType;
    private String userName;
    private String password;
    private String employeeAcceptStatus;
}
