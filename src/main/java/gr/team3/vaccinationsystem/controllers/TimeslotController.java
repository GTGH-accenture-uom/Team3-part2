package gr.team3.vaccinationsystem.controllers;

import gr.team3.vaccinationsystem.model.Timeslot;
import gr.team3.vaccinationsystem.service.TimeslotService;
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

    //Search timeslots by day
    @GetMapping("/SearchTimeslot")
    public List<Timeslot> SearchTimeslotByDayByMonthByYear(@RequestParam int day,
                                                           @RequestParam int month,
                                                           @RequestParam int year){
        return  TimeslotService.getFreeTimeslotsByDayByMonthByYear(day,month,year);
    }

    //Search timeslots by month
    @GetMapping("/SearchTimeslot")
    public List<Timeslot> SearchTimeslotByMonthByYear(@RequestParam int month,
                                                      @RequestParam int year){
        return  TimeslotService.getFreeTimeslotsByMonthByYear(month,year);
    }
}
