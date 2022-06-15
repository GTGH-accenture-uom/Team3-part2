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
    @Autowired
    InsuredService insuredService = new InsuredService();
    @Autowired
    DoctorService doctorService = new DoctorService();
    @Autowired
    TimeslotService timeslotService = new TimeslotService();
    @Autowired
    VaccinationCenterService vaccinationCenterService = new VaccinationCenterService();


    //DONE CHECK IF DOCTOR BELONGS TO THE GIVEN TIMESLOT
    //DONE CHECK IF INSURED  HAS ALREADY MADE A RESERVATION
    //http://localhost:8181/createReservation?amka=22024204689&timeslot=2022-06-12 02:00&doctor_name=Panagiotis&doctor_surname=Panagiotidis
    @PostMapping(path = "/createReservation")
    public String createReservation(@RequestParam (name = "amka") String amka,
                                    @RequestParam(name = "timeslot") String date,
                                    @RequestParam(name = "doctor_name") String doctor_name,
                                    @RequestParam(name = "doctor_surname") String doctor_surname){
        Insured insured = insuredService.getInsuredByAmka(amka);
        if (insured == null)
                return "insured with the given amka doesn't exist";
        if (insured.checkIfHasReservation())
            return "you already have a reservation";
        List<String> name_surname = new ArrayList<>();
        name_surname.add(doctor_name);
        name_surname.add(doctor_surname);
        Doctor doctor = doctorService.getDoctorByNameSurname(name_surname);
        if (doctor == null){
            return "there is no doctor with such name and surname";
        }
        Timeslot timeslot = timeslotService.getTimeslotByDateTime(date);
        if (timeslot == null)
            return "there is not such timeslot";
        if (! doctor.checkifDoctorIsInTimeslot(timeslot))
            return  "this Doctor does not belong to the given Timeslot";
         return reservationService.createReservation(insured,timeslot,vaccinationCenterService.getCenterByTimeslot(timeslot));
        //return "reservation created!";
    }


    //Change the reservation
    //http://localhost:8181/changeReservation?amka=22024204689&timeslot=2022-06-12 02:00&doctor_name=Panagiotis&doctor_surname=Panagiotidis
    @PostMapping(path = "/changeReservation")
    public String changeReservation(@RequestParam (name = "amka") String amka,
                                    @RequestParam(name = "timeslot") String date,
                                    @RequestParam(name = "doctor_name") String doctor_name,
                                    @RequestParam(name = "doctor_surname") String doctor_surname){

        Insured insured = insuredService.getInsuredByAmka(amka);
        if (insured == null)
            return "insured with the given amka doesn't exist";
        List<String> name_surname = new ArrayList<>();
        name_surname.add(doctor_name);
        name_surname.add(doctor_surname);
        Doctor doctor = doctorService.getDoctorByNameSurname(name_surname);
        if (doctor == null){
            return "there is no doctor with such name and surname";
        }
        Timeslot timeslot = timeslotService.getTimeslotByDateTime(date);
        if (timeslot == null || !timeslot.isFree())
            return "there is not such timeslot";
        if (! doctor.checkifDoctorIsInTimeslot(timeslot))
            return  "this Doctor does not belong to the given Timeslot";
        if(insured.getCount()<3){
            reservationService.changeReservation(insured);
            reservationService.createReservation(insured,timeslot,vaccinationCenterService.getCenterByTimeslot(timeslot));
            return "reservation updated!";
        }else {
            return "You can't change your reservation again!";
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
