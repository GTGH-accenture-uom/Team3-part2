package gr.team3.vaccinationsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.io.Serializable;
import java.util.ArrayList;


@Component
public class Reservation implements Serializable {

    Insured insuredPerson;
    @JsonIgnore
    Timeslot timeslot;
    Boolean isDone = false;
    @JsonIgnore
    VaccinationCenter vaccinationCenter;
    @JsonIgnore
    Doctor doctor;


    public Reservation(Insured insuredPerson, Doctor doctor, Timeslot timeslot, VaccinationCenter vaccinationCenter) {
        this.insuredPerson = insuredPerson;
        this.timeslot = timeslot;
        this.vaccinationCenter = vaccinationCenter;
        this.doctor = doctor;
    }


    public Reservation() {
    }


    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Insured getInsuredPerson() {
        return insuredPerson;
    }
    public void setInsuredPerson(Insured insuredPerson) {
        this.insuredPerson = insuredPerson;
    }
    public Timeslot getTimeslot() {
        return timeslot;
    }
    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public void setDone(boolean b) {
        isDone = b;
    }

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "insuredPerson=" + insuredPerson +
                ", timeslot=" + timeslot +
                ", isDone=" + isDone +
                ", vaccinationCenter=" + vaccinationCenter +
                '}';
    }


    public String getData(){
        StringBuilder data= new StringBuilder();
        data.append("amka: " +insuredPerson.getAmka()+"  ");
        data.append("date and time: " +timeslot.getLocalDateWithTime()+"  ");
        data.append("doctor: " +doctor.getName()+" "+doctor.getSurname()+"  ");
        data.append("vaccination center: " +vaccinationCenter.getCode());
        return data.toString();
    }


    public boolean getDone() {
        return isDone;
    }
}
