package com.vaccination.vaccinationdose.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamoDBTable(tableName = "Individual")
public class Individual {

    @DynamoDBHashKey(attributeName = "userName")
    private String userName;

    private List<DosageHistory> dosageHistory;
}
