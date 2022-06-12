package org.example.service;

import org.example.model.Insured;
import org.example.model.Reservation;
import org.example.model.Vaccination;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
This class represents the service for all the vaccinations.
It contains a list of all the vaccination , and some needed methods
such the creation of a vaccination.
 */
public class VaccinationService {

    private static List<Vaccination> vaccinationslist = new ArrayList<>();

    public void makeVaccination(Reservation reservation){
        reservation.setDone(true);
        LocalDate expiration_date = reservation.getTimeslot().getLocalDate().plusMonths(2);
        Vaccination vacc = new Vaccination(reservation.getInsuredPerson(), reservation.getDoctor(),reservation.getTimeslot().getLocalDate(), expiration_date);
        vaccinationslist.add(vacc);
        reservation.getDoctor().addVaccination(vacc);
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
