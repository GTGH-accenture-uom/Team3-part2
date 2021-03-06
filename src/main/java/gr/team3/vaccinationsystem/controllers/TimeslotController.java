package gr.team3.vaccinationsystem.controllers;

import gr.team3.vaccinationsystem.model.Doctor;
import gr.team3.vaccinationsystem.model.Timeslot;
import gr.team3.vaccinationsystem.service.DoctorService;
import gr.team3.vaccinationsystem.service.TimeslotService;
import gr.team3.vaccinationsystem.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TimeslotController {
    @Autowired
    TimeslotService timeslotService = new TimeslotService();
    @Autowired
    DoctorService doctorService = new DoctorService();
    @Autowired
    VaccinationCenterService vaccinationCenterService = new VaccinationCenterService();


    //Creates a new timeslot
    //http://localhost:8181/createTimeslot
    @PostMapping(path = "/createTimeslot")
    public String createTimeslot(@RequestBody Timeslot timeslot){
        return timeslotService.addTimeslot(timeslot);
    }


    //Get the list of all the timeslots
    //http://localhost:8181/timeslots
    @GetMapping(path = "/timeslots")
    public List<String> getAllTimeslots(){
        return timeslotService.getCustomTimeslotList();
    }


    //Delete a timeslot
    //http://localhost:8181/deleteTimeslot?ID=1
    @DeleteMapping(path = "/deleteTimeslot")
    public String deleteTimeslot(@RequestParam(name = "ID") Integer ID){
        return timeslotService.deleteTimeslotByID(ID);
    }


    //Show a Timeslot by ID
    //http://localhost:8181/showTimeslot?ID=1
    @GetMapping(path = "/showTimeslot")
    public Timeslot showDoctorByAmka(@RequestParam(name = "ID") Integer ID){
        return timeslotService.getTimeslotbyID(ID);
    }


    //Search free timeslots by inserting the desired day, month and year
    //Shows the free timeslots of only that day
    //http://localhost:8181/SearchTimeslots
    @GetMapping(path = "/SearchTimeslots")
    public List<String> SearchTimeslots(@RequestParam(name = "day") int day,
                                          @RequestParam(name= "month") int month,
                                          @RequestParam(name= "year") int year){
        return    timeslotService.getFreeTimeslots(day,month,year);
    }


    //Search free timeslots by inserting the desired month and year
    //Shows the free timeslots of that whole month
    //http://localhost:8181/SearchTimeslotsByMonth
    @GetMapping(path = "/SearchTimeslotsByMonth")
    public List<String> SearchTimeslotsByMonth(@RequestParam(name = "month") int month,
                                                 @RequestParam(name = "year") int year){
        return timeslotService.getFreeTimeslotsByMonth(month,year);
    }


}
