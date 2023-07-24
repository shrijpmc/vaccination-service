package com.vaccination.vaccinationdose.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "VaccinationDetail")
public class VaccinationDetail {

    @DynamoDBHashKey(attributeName = "region")
    private String region;

    @DynamoDBVersionAttribute(attributeName = "noOfDosage")
    private Integer noOfDosage;


    private List<Detail> vaccinationDetail;

}
