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

    /*
    This method creates an endpoint that's responsible for submitting a new
    vaccination. It receives some parameters from the URL and calls checkData() of vaccinationService
    to check if all the data are valid. This method returns an error message, or just an empty string
    if all the data are valid. Then, it checks if the return message was the empty string, and calls
    make vaccination of vaccinationService. Otherwise, it doesn't create the vaccination and retuns
    the error message.
     */
    //http://localhost:8181/makeVaccination?timeslot_ID=1&amka=24121101368&expiration_date=2022-06-02
    @PostMapping(path = "/makeVaccination")
    public String makeVaccination(@RequestParam(name = "timeslot_ID") Integer ID,
                                    @RequestParam(name = "amka") String amka,
                                    @RequestParam(name = "expiration_date") String exp_date){
        String message = vaccinationService.checkData(ID, amka, exp_date);
        if (message.equals("")) {
            Timeslot timeslot = timeslotService.getTimeslotbyID(ID);
            return vaccinationService.makeVaccination(reservationService.getReservationByAmkaAndTimeslot(amka, timeslot), exp_date);
        }
        else
            return message;
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
