package com.vaccination.vaccinationdose.entity;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
public class Detail {

    private String vaccinationCenter;
    private String vaccinationType;
    private String vaccinationDate;
    private String individualUserName;
}
