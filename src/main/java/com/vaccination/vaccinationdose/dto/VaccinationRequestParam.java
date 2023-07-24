package com.vaccination.vaccinationdose.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationRequestParam implements Serializable {

    private String individualUserName;
    private Integer noOfDosage;
    private String region;
    private String vaccinationCenter;
    private String vaccinationType;
    private String vaccinationDate;
}

