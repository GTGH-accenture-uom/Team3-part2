package gr.team3.vaccinationsystem.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Doctor {
    private String amka;
    private String name;
    private String surname;
    private List<Timeslot> timeslots;
    private List<Vaccination> vaccinations = new ArrayList<>();


    public Doctor(String amka, String name, String surname, List<Timeslot> timeslots) {
        this.amka = amka;
        this.name = name;
        this.surname = surname;
        this.timeslots = timeslots;
    }

    public Doctor(String amka, String name, String surname) {
        this.amka = amka;
        this.name = name;
        this.surname = surname;
        timeslots = new ArrayList<>();

    }

    public Doctor() {
    }

    public String getAmka() {return amka;}
    public void setAmka(String amka) {this.amka = amka;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getSurname() {return surname;}
    public void setSurname(String surname) {this.surname = surname;}

    public List<Timeslot> getTimeslots() { return timeslots; }

    public void setTimeslots(List<Timeslot> timeslots) {this.timeslots = timeslots;}

    public void addTimeslot(Timeslot timeslot){
        this.timeslots.add(timeslot);
    }

    public void addVaccination(Vaccination vaccination){
        vaccinations.add(vaccination);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "amka='" + amka + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    //This method is used to print the vaccinations done by the doctor
    public List<String> printDoneVaccinations(){
        List<String> results = new ArrayList<>();
        results.add("Vaccinations of Dr " + name +" " +surname +":");
        System.out.println(results.get(0));
        for (Vaccination vacc: vaccinations) {
            results.add((vacc.getVaccinationDate() +" : " +vacc.getInsuredPerson().getName() + " " + vacc.getInsuredPerson().getSurname()).toString());
            System.out.println(vacc.getVaccinationDate() +" : " +vacc.getInsuredPerson().getName() + " " + vacc.getInsuredPerson().getSurname());
        }
        return results;
    }
}