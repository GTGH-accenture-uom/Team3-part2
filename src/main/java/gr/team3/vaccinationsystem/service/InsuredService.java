package gr.team3.vaccinationsystem.service;

import gr.team3.vaccinationsystem.model.Insured;
import gr.team3.vaccinationsystem.model.Reservation;
import gr.team3.vaccinationsystem.model.Vaccination;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
This class represents the service for all the insured people.
It contains a list of all the insured people, and some needed methods
such as the creation of an insured person.
 */
@Service
public class InsuredService {

   private static List<Insured> insuredList = new ArrayList<Insured>();

    //creates new Insured object and adds it to the list
    public void createInsured(String afm, String amka, String name, String surname, LocalDate birthdate, String email)
    {
        insuredList.add(new Insured(afm, amka ,name, surname,birthdate, email ));
    }

    //Returns the list with all the insured people.
    public List<Insured> getAllInsured()
    {
        return insuredList;
    }

    public  Insured getInsuredByAmka(String s) {
        for (Insured insured: InsuredService.insuredList) {
            if (insured.getAmka().equals(s))
                return insured;
        }
        return null;
    }

    /*
    this method finds and prints the insured people over the age of 60 that have not made
    a reservation. It iterates the insuredList, finds the people that are over 60 years old,
    and then checks if that specific person exists in a reservation object of the reservationsList from
    the reservationService class.
     */
    public List<String> printInsuredOverSixtyWithNoAppointment() {
        List<String> results = new ArrayList<>();
        results.add("Insured people over the age of 60 that have not made a reservation: ");
        boolean hasAppointment = false;
        ArrayList<Insured> eligibleInsured = new ArrayList<>();
        for (Insured insured:insuredList) {
            if (LocalDate.now().getYear()- insured.getBirthdate().getYear() >= 60) {
                for (Reservation res : ReservationService.getReservationList()) {
                    if (res.getInsuredPerson().equals(insured)) {
                        hasAppointment = true;
                        break;
                    }
                }
                if (!hasAppointment) {
                    eligibleInsured.add(insured);
                    results.add(insured.toString());
            }

            }
            hasAppointment = false;
        }
        System.out.println(results.get(0));
        eligibleInsured.forEach(System.out::println);
        return results;
    }

    //Checks and prints if the insured person's vaccination coverage has expired
    //or not  and the expiration date depending on the vaccination they had.It
    //also checks and prints if the insured has a vaccination record
    public  String checkHasCoverage(Insured insured) {
        for (Vaccination vaccination1 : VaccinationService.getVaccinationslist()) {
            if (vaccination1.getInsuredPerson().equals(insured)){

                if ((vaccination1.getExpirationDate().isAfter(LocalDate.now()))) {
                    return ("Your vaccination certificate is still valid! It expires at " + vaccination1.getExpirationDate());
                }
                else
                    return "Your vaccination coverage has expired!";
            }
        }
        return "No vaccination record found!";
    }
}
