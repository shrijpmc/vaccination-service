package com.vaccination.vaccinationdose.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationSummary {

    private Integer dosagePercentage;
    private Set<String> activeVaccinationCenter;
    private Set<String> availableVaccinationTypes;
    private String region;
}
