package gr.team3.vaccinationsystem.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Component
public class VaccinationCenter implements Serializable {
    private String code;
    private String address;
    private List<Timeslot> timeslots;


    public VaccinationCenter(String code, String address) {
        this.code = code;
        this.address = address;
        timeslots = new ArrayList<>();
    }


    public VaccinationCenter(String code, String address, ArrayList<Timeslot> timeslots) {
        this.code = code;
        this.address = address;
        this.timeslots = timeslots;
    }


    public VaccinationCenter() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Timeslot> getTimeslots() { return timeslots; }

    public void setTimeslots(List<Timeslot> timeslots) { this.timeslots = timeslots; }


    @Override
    public String toString() {
        return "VaccinationCenter{" +
                "code='" + code + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


    //adds a new timeslot to the vaccination center
    public void addTimeslot(Timeslot timeslot){
        timeslots.add(timeslot);
        timeslot.getDoctor().addTimeslot(timeslot);
    }

    public Timeslot getTimeslotByID(int timeslotId) {
        for (Timeslot timeslot:timeslots) {
            if (timeslot.getID().equals(timeslotId))
                return timeslot;

        }
        return null;
    }
}
