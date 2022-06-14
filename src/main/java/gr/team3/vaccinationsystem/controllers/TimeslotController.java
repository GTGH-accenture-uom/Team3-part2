package gr.team3.vaccinationsystem.controllers;

import gr.team3.vaccinationsystem.model.Timeslot;
import gr.team3.vaccinationsystem.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TimeslotController {
    @Autowired
    TimeslotService timeslotService = new TimeslotService();



    //Search free timeslots by inserting the desired day, month and year
    //Shows the free timeslots of only that day
    @GetMapping(path = "/SearchTimeslots")
    public List<Timeslot> SearchTimeslots(@RequestParam(name = "day") int day,
                                          @RequestParam(name= "month") int month,
                                          @RequestParam(name= "year") int year){
        return    timeslotService.getFreeTimeslots(day,month,year);
    }



    //Search free timeslots by inserting the desired month and year
    //Shows the free timeslots of that whole month
    @GetMapping(path = "/SearchTimeslotsByMonth")
    public List<Timeslot> SearchTimeslotsByMonth(@RequestParam(name = "month") int month,
                                                 @RequestParam(name = "year") int year){
        return timeslotService.getFreeTimeslotsByMonth(month,year);
    }


}
