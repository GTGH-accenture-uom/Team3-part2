package gr.team3.vaccinationsystem.service;

import gr.team3.vaccinationsystem.model.Insured;
import gr.team3.vaccinationsystem.model.Reservation;
import gr.team3.vaccinationsystem.model.Vaccination;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*
This class represents the service for all the vaccinations.
It contains a list of all the vaccination , and some needed methods
such the creation of a vaccination.
 */
@Service
public class VaccinationService {

    private static List<Vaccination> vaccinationslist = new ArrayList<>();

    public String makeVaccination(Reservation reservation, String expiration_date){
        //check if vaccination already exists
        for (Vaccination vacc:vaccinationslist) {
            if (vacc.getInsuredPerson().equals(reservation.getInsuredPerson()))
                return "vaccination already done!";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate exp_date = LocalDate.parse(expiration_date, formatter);
        if (exp_date.isBefore(reservation.getTimeslot().getLocalDate())){
            return "invalid expiration date";
        }
        reservation.setDone(true);
        Vaccination vacc = new Vaccination(reservation.getInsuredPerson(), reservation.getDoctor(),reservation.getTimeslot().getLocalDate(), exp_date);
        vaccinationslist.add(vacc);
        reservation.getDoctor().addVaccination(vacc);
        return "created vaccination!";
    }

    public static List<Vaccination> getVaccinationslist() {
        return vaccinationslist;
    }

    public static void setVaccinationslist(ArrayList<Vaccination> vaccinationslist) {
        VaccinationService.vaccinationslist = vaccinationslist;
    }


    //This method returns the vaccination of each insured people.
    public Vaccination getVaccinationbyInsured(Insured insuredPerson) {
        for (Vaccination vaccination:vaccinationslist) {
            if (vaccination.getInsuredPerson().equals(insuredPerson))
                    return vaccination;
        }
        return null;
    }

}
