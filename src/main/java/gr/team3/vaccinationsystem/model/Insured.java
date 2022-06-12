package org.example.model;

import java.time.LocalDate;

public class Insured {

    private String afm;
    private String amka;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String email;

    public Insured(String afm, String amka, String name, String surname, LocalDate birthdate, String email) {
        this.afm = afm;
        this.amka = amka;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.email = email;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Insured{" +
                "afm='" + afm + '\'' +
                ", amka='" + amka + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", email='" + email + '\'' +
                '}';
    }
}
