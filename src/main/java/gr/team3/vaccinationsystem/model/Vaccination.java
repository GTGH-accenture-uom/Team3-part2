package gr.team3.vaccinationsystem.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;


@Component
public class Vaccination implements Serializable {
    Insured insuredPerson;
    Doctor doctor;
    LocalDate vaccinationDate;
    LocalDate expirationDate;


    public Vaccination(Insured insuredPerson, Doctor doctor, LocalDate vaccinationDate, LocalDate expirationDate) {
        this.insuredPerson = insuredPerson;
        this.doctor = doctor;
        this.vaccinationDate = vaccinationDate;
        this.expirationDate = expirationDate;
    }


    public Vaccination() {
    }

    public Insured getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(Insured insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(LocalDate vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }


    public String getData() {
        StringBuilder data= new StringBuilder();
        data.append("amka: " +insuredPerson.toString()+"  ");
        data.append("Vaccination date: " + vaccinationDate+ " ");
        data.append("Expiration date: " +expirationDate + " ");
        data.append("doctor: " +doctor.getName()+" "+doctor.getSurname()+"  ");
        return data.toString();
    }
}


