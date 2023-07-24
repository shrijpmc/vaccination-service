package com.vaccination.vaccinationdose.repo;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.vaccination.vaccinationdose.entity.DosageHistory;
import com.vaccination.vaccinationdose.entity.Individual;
import com.vaccination.vaccinationdose.entity.VaccinationDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VaccinationRepository {

    @Autowired
    DynamoDBMapper dynamoDBMapper;


    public void saveVaccinationDetail(VaccinationDetail vaccinationDetail) {
        dynamoDBMapper.save(vaccinationDetail);

    }

    public Individual findByUserName(String individualUserName) {
        Individual individual = new Individual();
        individual.setUserName(individualUserName);
        return dynamoDBMapper.load(individual);
    }

    public void saveIndividual(Individual individual) {
        dynamoDBMapper.save(individual);
    }

    public List<DosageHistory> getDosageHistory(String userName) {
       Individual individual = new Individual();
       individual.setUserName(userName);
        DynamoDBQueryExpression<Individual> queryExpression = new DynamoDBQueryExpression<Individual>()
                .withHashKeyValues(individual);

        List<Individual> result = dynamoDBMapper.query(Individual.class, queryExpression);
        List<DosageHistory> dosageHistories = result.get(0).getDosageHistory();
        return dosageHistories;
    }

    public VaccinationDetail getVaccinationDetail(String region) {
        VaccinationDetail vaccinationDetail = new VaccinationDetail();
        vaccinationDetail.setRegion(region);
        return dynamoDBMapper.load(vaccinationDetail);
    }
}
