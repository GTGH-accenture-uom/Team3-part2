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



    /*
        This method creates an endpoint that's responsible for submitting a new
        reservation. It receives some parameters from the URL and calls checkData() of reservationService
        to check if all the data are valid. This method returns an error message if it finds
        some invalid parameter, or an empty string if all the data are correct.
        Then, it checks if the return message was the empty string, and calls
        makeReservation of reservationService. Otherwise, it doesn't create the reservation and returns
        the error message.
         */
    //http://localhost:8181/createReservation?amka=22024204689&timeslot=2022-06-12 02:00&doctor_name=Panagiotis&doctor_surname=Panagiotidis
    @PostMapping(path = "/createReservation")
    public String createReservation(@RequestParam (name = "amka") String amka,
                                    @RequestParam(name = "ID") Integer ID,
                                    @RequestParam(name = "doctor_name") String doctor_name,
                                    @RequestParam(name = "doctor_surname") String doctor_surname){
        String message = reservationService.checkAllData(amka,ID,doctor_name,doctor_surname);
        if (message.equals("")) {
            return reservationService.createReservation(amka, ID);
        }
        else
            return message;
    }


    /*
    This method is responsible for changing a reservation based on the parameters given by
    the URL. First, it calls checkData and receives an error message if any of the data are invalid.
    If the return value was an empty string, it means that all the data were valid and continues with
    calling changeReservation() and createReservation method()
     */
    //http://localhost:8181/changeReservation?amka=22024204689&timeslot=2022-06-12 02:00&doctor_name=Panagiotis&doctor_surname=Panagiotidis
    @PutMapping(path = "/changeReservation")
    public String changeReservation(@RequestParam (name = "amka") String amka,
                                    @RequestParam(name = "timeslot") Integer ID,
                                    @RequestParam(name = "doctor_name") String doctor_name,
                                    @RequestParam(name = "doctor_surname") String doctor_surname){

        String message = reservationService.checkAllData(amka,ID,doctor_name,doctor_surname);
        if (message.equals("")){
            if (!reservationService.checkIfHasReservation(amka))
                return "There is no reservation with this amka";
            reservationService.changeReservation(amka);
            reservationService.createReservation(amka,ID);
            return "reservation updated!";
        }else {
            return message;
        }

    }


    //Delete a reservation by amka
    //http//localhost:8181/deleteReservationByAmka?amka=22024204689
    @DeleteMapping(path = "/deleteReservationByAmka")
    public String deleteReservationByAmka(@RequestParam(name = "amka") String amka){
        return reservationService.deleteReservationByAmka(amka);
    }


    //Get the list of all the reservations
    //http://localhost:8181/reservationList
    @GetMapping(path = "/reservationList")
    public List<String> getReservations(){
        return reservationService.getCustomReservationList();
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
    //http://localhost:8181/futureReservations
    @GetMapping("/futureReservations")
    public List<String> getFutureReservations(){

        return reservationService.getFutureReservationList();
    }


    //Get reservation list by day
    //http://localhost:8181/reservationByDay?day=6&month=9&year=2022
    @GetMapping(path = "/reservationListByDay")
    public List<String> reservationListByDay(@RequestParam(name = "day")int day,
                                          @RequestParam(name="month") int month,
                                          @RequestParam(name="year") int year){
        return    reservationService.getReservationListByDay(day,month,year);
    }

}
