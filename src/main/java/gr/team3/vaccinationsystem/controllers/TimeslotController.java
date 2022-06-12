package gr.team3.vaccinationsystem.controllers;

import gr.team3.vaccinationsystem.model.Timeslot;
import gr.team3.vaccinationsystem.service.InsuredService;
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

    //Search timeslots by day
    @GetMapping(path = "/SearchTimeslot")
    public List<Timeslot> SearchTimeslotByDayByMonthByYear(@RequestParam(name = "day")int day,
                                                           @RequestParam(name="month") int month,
                                                           @RequestParam(name="year") int year){
        return    timeslotService.getFreeTimeslotsByDayByMonthByYear(day,month,year);
    }

    //Search timeslots by month
    @GetMapping(path = "/SearchTimeslotByMonth")
    public List<Timeslot> SearchTimeslotByMonthByYear(@RequestParam(name = "month") int month,
                                                      @RequestParam(name = "year") int year){
        return   timeslotService.getFreeTimeslotsByMonthByYear(month,year);
    }

}
