package gr.team3.vaccinationsystem.service;

import gr.team3.vaccinationsystem.model.Doctor;
import gr.team3.vaccinationsystem.model.Insured;
import gr.team3.vaccinationsystem.model.Reservation;
import gr.team3.vaccinationsystem.model.Vaccination;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
        Pattern amkaPattern = Pattern.compile("\\d{11}");
        if(getInsuredByAmka(amka)== null){
            if(amkaPattern.matcher(amka).matches()){
                insuredList.add(new Insured(afm, amka ,name, surname,birthdate, email));
                //System.out.println("Insured created!"); this should go in controller
            }else{
                System.out.println("Amka is not valid");
            }

        }

    }


    //Returns the list with all the insured people.
    public List<Insured> getAllInsured()
    {
        return insuredList;
    }

    public List<Object> getAllInsuredAsObjects()
    {
        List<Object> list = new ArrayList<>();
        list.addAll(insuredList);
        return list;
    }


    //Get an insured through his amka
    public Insured getInsuredByAmka(String s) {
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
    public  String checkHasCoverage(String amka) {
        Insured insured = getInsuredByAmka(amka);
        VaccinationService vaccinationService = new VaccinationService();
        if (insured == null) {
            return "The insured with the given amka doesn't exist";
        }
        else
            {
                for (Vaccination vaccination1 : vaccinationService.getVaccinationslist()) {
                    if (vaccination1.getInsuredPerson().equals(insured)) {
                        if ((vaccination1.getExpirationDate().isAfter(LocalDate.now()))) {
                            return ("Your vaccination certificate is still valid! It expires at " + vaccination1.getExpirationDate());
                        } else
                            return "Your vaccination coverage has expired!";
                    }
                }
                return "No vaccination record found!";
            }
        }




    //Deletes an insured that already exists using the insured amka
    public String deleteInsuredByAmka(String amka) {
        Insured insured = this.getInsuredByAmka(amka);
        if (insured == null) {
            return "The insured with the given amka doesn't exist";
        }
        else
        {
            insuredList.remove(insured);
            return "Insured with the: " + amka + " amka successfully deleted";
        }
    }


    public void setInsuredList(List<Insured> readAllInsured) {
        insuredList = readAllInsured;
    }
}
