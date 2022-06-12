package gr.team3.vaccinationsystem.controllers;

import gr.team3.vaccinationsystem.model.Doctor;
import gr.team3.vaccinationsystem.model.Insured;
import gr.team3.vaccinationsystem.model.Timeslot;
import gr.team3.vaccinationsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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

    @PostMapping(path = "/createReservation")
    public String createReservation(@RequestParam (name = "amka") String amka,
                                    @RequestParam(name = "timeslot")LocalDate date,
                                    @RequestParam(name = "doctor_name") String doctor_name,
                                    @RequestParam(name = "doctor_surname") String doctor_surname){
        Insured insured = InsuredService.getInsuredByAmka(amka);
        if (insured == null)
                return "insured with the given amka doesn't exist";
        List<String> name_surname = new ArrayList<>();
        name_surname.add(doctor_name);
        name_surname.add(doctor_surname);
        Doctor doctor = doctorService.getDoctorByNameSurname(name_surname);
        if (doctor == null){
            return "there is no doctor with such name and surname";
        }
        Timeslot timeslot = timeslotService.getTimeslotByDate(date);
        if (timeslot == null)
            return "there is not such timeslot";
        reservationService.createReservation(insured,timeslot,vaccinationCenterService.getCenterByTimeslot(timeslot));
        return "reservation created!";
    }
}
