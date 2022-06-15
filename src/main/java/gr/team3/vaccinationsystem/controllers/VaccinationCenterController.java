package gr.team3.vaccinationsystem.controllers;

import gr.team3.vaccinationsystem.model.Timeslot;
import gr.team3.vaccinationsystem.model.VaccinationCenter;
import gr.team3.vaccinationsystem.service.TimeslotService;
import gr.team3.vaccinationsystem.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class VaccinationCenterController {

    @Autowired
    VaccinationCenterService vaccinationCenterService = new VaccinationCenterService();


    //Assign timeslot to center
    //http://localhost:8181/assignTimeslotToCenter?
    @PostMapping(path = "/assignTimeslotToCenter")
    public String assignTimeslotToCenterByAmka(@RequestParam(name = "centerId") String centerCode,
                                               @RequestParam(name = "timeslotId")int timeslotId){
        return vaccinationCenterService.assignTimeslotToCenter(centerCode, timeslotId);
    }


    //Creates a new center
    //http://localhost:8181/createCenter
    @PostMapping(path = "/createCenter")
    public String createCenter(@RequestBody VaccinationCenter center){
        return vaccinationCenterService.createVaccinationCenter(center);
    }


    //Get the list of all the centers
    //http://localhost:8181/centers
    @GetMapping(path = "/centers")
    public List<VaccinationCenter> getAllCenters(){
        return vaccinationCenterService.getAllCenters();
    }


    //Delete a center by code
    //http://localhost:8181/deleteCenter?code=1
    @DeleteMapping(path = "/deleteCenter")
    public String deleteCenter(@RequestParam(name = "code") String code){
        return vaccinationCenterService.deleteCenterByCode(code);
    }


    //Show a center by Code
    //http://localhost:8181/showCenter?code=01
    @GetMapping(path = "/showCenter")
    public VaccinationCenter showDoctorByAmka(@RequestParam(name = "code") String code){
        return vaccinationCenterService.getCenterByCode(code);
    }

}
