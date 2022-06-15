package gr.team3.vaccinationsystem.controllers;

import gr.team3.vaccinationsystem.model.Doctor;
import gr.team3.vaccinationsystem.model.Insured;
import gr.team3.vaccinationsystem.model.Reservation;
import gr.team3.vaccinationsystem.model.Timeslot;
import gr.team3.vaccinationsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService = new ReservationService();




    //http://localhost:8181/createReservation?amka=22024204689&timeslot=2022-06-12 02:00&doctor_name=Panagiotis&doctor_surname=Panagiotidis
    @PostMapping(path = "/createReservation")
    public String createReservation(@RequestParam (name = "amka") String amka,
                                    @RequestParam(name = "timeslot") Integer ID,
                                    @RequestParam(name = "doctor_name") String doctor_name,
                                    @RequestParam(name = "doctor_surname") String doctor_surname){
        String message = reservationService.checkAllData(amka,ID,doctor_name,doctor_surname);
        if (message.equals("")) {
            return reservationService.createReservation(amka, ID);

        }
        else
            return message;
    }


    //Change the reservation
    //http://localhost:8181/changeReservation?amka=22024204689&timeslot=2022-06-12 02:00&doctor_name=Panagiotis&doctor_surname=Panagiotidis
    @PostMapping(path = "/changeReservation")
    public String changeReservation(@RequestParam (name = "amka") String amka,
                                    @RequestParam(name = "timeslot") Integer ID,
                                    @RequestParam(name = "doctor_name") String doctor_name,
                                    @RequestParam(name = "doctor_surname") String doctor_surname){

        String message = reservationService.checkAllData(amka,ID,doctor_name,doctor_surname);
        if (message.equals("")){
            reservationService.changeReservation(amka);
            reservationService.createReservation(amka,ID);
            return "reservation updated!";
        }else {
            return message;
        }

    }


    //Delete a reservation by amka
    //http//localhost:8181/deleteReservation?amka=22024204689
    @DeleteMapping(path = "/deleteReservationByAmka")
    public String deleteReservationByAmka(@RequestParam(name = "amka") String amka){
        return reservationService.deleteReservationByAmka(amka);
    }


    //Get the list of all the reservations
    //http://localhost:8181/reservationList
    @GetMapping(path = "/reservationList")
    public List<Reservation> getReservations(){
        return reservationService.getReservations();
    }


    //Get reservation by amka
    //http://localhost:8181/getReservationByamka?amka=22024204689
    @GetMapping(path="/getReservationByamka")
    public Reservation getReservationByAmka(@RequestParam(name = "amka") String amka){
        return reservationService.getReservationByAmka(amka);
    }


    //Get reservation by amka and timeslot
    //http://localhost:8181/getReservationByamka?amka=22024204689&timeslot=2022-06-12 02:00
    @GetMapping(path="/getReservationByAmkaAndTimeslot")
    public Reservation getReservationByAmkaAndTimeslot(@RequestParam(name = "amka") String amka,
                                                       @RequestParam(name = "timeslot") Timeslot timeslot){
        return reservationService.getReservationByAmkaAndTimeslot(amka,timeslot);
    }


    //Get all future reservations
    //localhost:8181/futureReservations
    @GetMapping("/futureReservations")
    public List<String> getFutureReservations(){

        return reservationService.getFutureReservationList();
    }


    //Get reservation list by day
    //localhost:8181/reservationByDay?day=6&month=9&year=2022
    @GetMapping(path = "/reservationListByDay")
    public List<String> reservationListByDay(@RequestParam(name = "day")int day,
                                          @RequestParam(name="month") int month,
                                          @RequestParam(name="year") int year){
        return    reservationService.getReservationListByDay(day,month,year);
    }

}
