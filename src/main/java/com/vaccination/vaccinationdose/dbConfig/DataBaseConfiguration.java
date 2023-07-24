package com.vaccination.vaccinationdose.dbConfig;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBaseConfiguration {


    @Value("${aws.isam.access.key}")
    private String accessKey;

    @Value("${aws.isam.secrete.access.key}")
    private String secreteAccessKey;

    @Value("${aws.region}")
    private String region;


    @Bean
    public DynamoDBMapper getDynamoDBMapper(){
        AWSCredentialsProvider credentials= new AWSStaticCredentialsProvider
                (new BasicAWSCredentials(accessKey, secreteAccessKey));

        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(credentials)
                .withRegion(region)
                .build();

        return new DynamoDBMapper(dynamoDBClient);
    }

}
