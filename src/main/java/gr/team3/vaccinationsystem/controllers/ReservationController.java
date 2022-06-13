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
    //TODO CHECK IF INSURED  HAS ALREADY MADE A RESERVATION
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
        if ( doctor.checkifDoctorIsInTimeslot(timeslot))
            return  "this Doctor does not belong to the given Timeslot";
        reservationService.createReservation(insured,timeslot,vaccinationCenterService.getCenterByTimeslot(timeslot));
        return "reservation created!";
    }


    @PutMapping("/changeReservation")
    public String changeReservation(@RequestParam() String amka) {
        Insured insured = insuredService.getInsuredByAmka(amka);
        if(insured==null){
            return"Does not exist insured with this amka";
        }
        Reservation reservation = reservationService.getReservationByAmka(amka);
        if(reservation == null){
            return "Does not exist reservation for insured " +insured.getName();
        }
        reservationService.changeReservation(insured, reservation.getTimeslot(),reservation.getVaccinationCenter());
        return "Τhe reservation has been changed";
    }

//    @GetMapping("/FutureReservations")
//    public List<Reservation> getFutureReservations(){
//        return reservationService.getFutureReservationList();
//    }

}
