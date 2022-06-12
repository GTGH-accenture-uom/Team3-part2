package org.example.model;

import org.example.model.Doctor;
import org.example.model.Insured;

import java.time.LocalDate;

public class Vaccination {
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


    }


