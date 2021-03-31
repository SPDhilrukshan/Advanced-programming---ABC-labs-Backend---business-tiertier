package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class LabTestDAO {
    private Long labTestId;

    private String labTestName;
    private String labTestdescription;
    private Long labTestcost;
}
