package com.vaccination.vaccinationdose.service;

import com.vaccination.vaccinationdose.aop.Log;
import com.vaccination.vaccinationdose.dto.VaccinationRequestParam;
import com.vaccination.vaccinationdose.dto.VaccinationSummary;
import com.vaccination.vaccinationdose.entity.Detail;
import com.vaccination.vaccinationdose.entity.DosageHistory;
import com.vaccination.vaccinationdose.entity.Individual;
import com.vaccination.vaccinationdose.entity.VaccinationDetail;
import com.vaccination.vaccinationdose.repo.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class VaccinationService {

    @Autowired
    VaccinationRepository vaccinationRepository;


    @Log
    public void addVaccinationDetails(VaccinationRequestParam requestParam) {
        // prepare vaccination detail. Check whether there is an entry for particular region.

        VaccinationDetail existingVaccinationDetail = vaccinationRepository.getVaccinationDetail(requestParam.getRegion());
        if(null != existingVaccinationDetail){
            existingVaccinationDetail.getVaccinationDetail().add(prepareDetails(requestParam));

            //saving vaccination detail.
            vaccinationRepository.saveVaccinationDetail(existingVaccinationDetail);
        }else{
            VaccinationDetail vaccinationDetail = new VaccinationDetail();
            vaccinationDetail.setRegion(requestParam.getRegion());
            List<Detail> details = new ArrayList<>();
            details.add( prepareDetails(requestParam));

            vaccinationDetail.setVaccinationDetail(details);

            //saving vaccination detail.
            vaccinationRepository.saveVaccinationDetail(vaccinationDetail);
        }

        // prepare data for Individual dosage.
        Individual existingIndividual = vaccinationRepository.findByUserName(requestParam.getIndividualUserName());
        if (null != existingIndividual && !existingIndividual.getDosageHistory().isEmpty()) {
            List<DosageHistory> existingDosages = existingIndividual.getDosageHistory();
            existingDosages.add(prepareDosageHistory(requestParam));
            vaccinationRepository.saveIndividual(existingIndividual);
        } else {
            Individual individual = new Individual();
            individual.setUserName(requestParam.getIndividualUserName());
            List<DosageHistory> dosageHistories = new ArrayList<>();
            dosageHistories.add(prepareDosageHistory(requestParam));
            individual.setDosageHistory(dosageHistories);
            vaccinationRepository.saveIndividual(individual);
        }

    }

    private Detail prepareDetails(VaccinationRequestParam requestParam) {
        Detail detail = new Detail(requestParam.getVaccinationCenter(),
                requestParam.getVaccinationType(),
                requestParam.getVaccinationDate(),
                requestParam.getIndividualUserName());
        return detail;
    }

    private DosageHistory prepareDosageHistory(VaccinationRequestParam vaccinationDetail) {
        return new DosageHistory(
                vaccinationDetail.getVaccinationType(),
                vaccinationDetail.getVaccinationDate(),
                vaccinationDetail.getVaccinationCenter(),
                vaccinationDetail.getNoOfDosage());
    }

    public List<DosageHistory> getDosageHistory(String userName) {
       return vaccinationRepository.getDosageHistory(userName);
    }

    public VaccinationSummary getVaccinationSummary(String region) {
        VaccinationDetail vaccinationDetail = vaccinationRepository.getVaccinationDetail(region);
        Set<String> vc = vaccinationDetail.getVaccinationDetail().stream().map(Detail::getVaccinationCenter).collect(Collectors.toSet());
        Set<String> vt = vaccinationDetail.getVaccinationDetail().stream().map(Detail::getVaccinationType).collect(Collectors.toSet());
        VaccinationSummary summary = new VaccinationSummary(vaccinationDetail.getNoOfDosage(),
                vc,vt,region);
        return summary;
    }
}