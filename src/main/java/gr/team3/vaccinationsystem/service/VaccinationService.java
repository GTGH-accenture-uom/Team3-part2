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


    //This method makes a vaccination
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


    public List<Vaccination> getVaccinationslist() {
        return vaccinationslist;
    }


    //This method returns the vaccination of each insured people.
    public Vaccination getVaccinationbyInsured(Insured insuredPerson) {
        for (Vaccination vaccination:vaccinationslist) {
            if (vaccination.getInsuredPerson().equals(insuredPerson))
                    return vaccination;
        }
        return null;
    }


    public void setVaccinationList(List<Vaccination> list) {
        vaccinationslist = list;
    }


    public List<Object> getAllAsObjects() {

        List<Object> list = new ArrayList<>();
        list.addAll(vaccinationslist);
        return list;
    }


    //This method returns the vaccinations by amka.
     public Vaccination getVaccinationbyAmka(String amka) {
        for(Vaccination vaccination:vaccinationslist){
            if(vaccination.getInsuredPerson().getAmka().equals(amka)){
                return vaccination;
            }
        }
        return null;
    }


    public List<String> getCustomVaccinationslist() {
        List<String> customList = new ArrayList<>();
        for (Vaccination vaccination:vaccinationslist) {
            customList.add(vaccination.getData());
        }
        return customList;
    }
}
