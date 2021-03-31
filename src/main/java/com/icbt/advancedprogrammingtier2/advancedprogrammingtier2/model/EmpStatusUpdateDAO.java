package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmpStatusUpdateDAO {
    private Long employeeId;
    private String employeeStatus;
}
