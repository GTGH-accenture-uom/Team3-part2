package gr.team3.vaccinationsystem.controllers;

import gr.team3.vaccinationsystem.model.Insured;
import gr.team3.vaccinationsystem.model.Reservation;
import gr.team3.vaccinationsystem.model.Timeslot;
import gr.team3.vaccinationsystem.model.Vaccination;
import gr.team3.vaccinationsystem.service.InsuredService;
import gr.team3.vaccinationsystem.service.ReservationService;
import gr.team3.vaccinationsystem.service.TimeslotService;
import gr.team3.vaccinationsystem.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VaccinationController {

    @Autowired
    TimeslotService timeslotService = new TimeslotService();
    @Autowired
    ReservationService reservationService = new ReservationService();
    @Autowired
    InsuredService insuredService = new InsuredService();
    @Autowired
    VaccinationService vaccinationService = new VaccinationService();



    //done check if vaccination already done
    //http://localhost:8181/makeVaccination?timeslot_ID=1&amka=24121101368&expiration_date=2022-06-02
    @PostMapping(path = "/makeVaccination")
    public String makeVaccination(@RequestParam(name = "timeslot_ID") Integer ID,
                                    @RequestParam(name = "amka") String amka,
                                    @RequestParam(name = "expiration_date") String exp_date){
        Timeslot timeslot = timeslotService.getTimeslotbyID(ID);
        System.out.println(timeslot);
        if (timeslot == null)
            return "Timeslot with ID: " + ID + " doesn't exist";
        Insured insured = insuredService.getInsuredByAmka(amka);
        if (insured == null)
            return "insured with the given amka doesn't exist";
        Reservation reservation = reservationService.getReservationByAmkaAndTimeslot(amka, timeslot);
        //System.out.println(reservation);
        if (reservation == null)
            return "there is not such reservation with the given amka and timeslot";

        return vaccinationService.makeVaccination(reservation,exp_date);
    }

    //Get the list of all the vaccinations
    //http://localhost:8181/vaccinationList
    @GetMapping(path = "/vaccinationList")
    public List<String> getVaccinationList(){
        return vaccinationService.getCustomVaccinationslist();
    }

    //GetVaccination of each insured person
    //http://localhost:8181/getVaccinationByamka?amka=18029704689
    @GetMapping(path="/getVaccinationByamka")
    public Vaccination getVaccinationbyAmka(@RequestParam(name = "amka") String amka){
        return vaccinationService.getVaccinationbyAmka(amka);
    }

}
