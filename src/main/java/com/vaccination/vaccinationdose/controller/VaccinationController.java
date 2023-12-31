package com.vaccination.vaccinationdose.controller;


import com.vaccination.vaccinationdose.aop.Log;
import com.vaccination.vaccinationdose.dto.VaccinationRequestParam;
import com.vaccination.vaccinationdose.dto.VaccinationSummary;
import com.vaccination.vaccinationdose.entity.DosageHistory;
import com.vaccination.vaccinationdose.service.VaccinationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccination")
public class VaccinationController {

    @Autowired
    VaccinationService vaccinationService;


    @Operation(summary = "Add Vaccination details. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Added Vaccination detail are added."),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")})
    @Log
    @PostMapping("/addDetails")
    public ResponseEntity<String> addVaccinationDetails(@RequestBody VaccinationRequestParam requestParam){
        vaccinationService.addVaccinationDetails(requestParam);
        return new ResponseEntity<String>("Vaccination details are added successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Get dosage history of an individual.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dosage history has been returned successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")})
    @Log
    @GetMapping("/dosageHistory/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<DosageHistory> getDosageHistory(@PathVariable("userName") String userName){
        List<DosageHistory> dosageHistories =  vaccinationService.getDosageHistory(userName);
        return dosageHistories;
    }

    @Operation(summary = "Get Vaccination summary by region name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Summary of vaccination by region"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")})
    @Log
    @GetMapping("/summary/{region}")
    @ResponseStatus(HttpStatus.OK)
    public VaccinationSummary  getVaccinationSummary(@PathVariable("region") String region){
      VaccinationSummary summary = vaccinationService.getVaccinationSummary(region);
      return summary;
    }

}
