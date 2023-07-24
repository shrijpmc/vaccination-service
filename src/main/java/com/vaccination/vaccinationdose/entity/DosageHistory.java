package com.vaccination.vaccinationdose.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
public class DosageHistory {

    private String vaccinationType;
    private String vaccinationDate;
    private String vaccinationCenter;
    private Integer dosageCount;


}
