
package gr.team3.vaccinationsystem.service;

import gr.team3.vaccinationsystem.FileParser;
import gr.team3.vaccinationsystem.model.Doctor;
import gr.team3.vaccinationsystem.model.Timeslot;
import gr.team3.vaccinationsystem.model.Vaccination;
import gr.team3.vaccinationsystem.model.VaccinationCenter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.temporal.JulianFields;
import java.util.ArrayList;
import java.util.List;


/*
This class represents the service for all the vaccination centers.
It contains a list of all the vaccination centers, and some needed methods
such the creation of a vaccination center.
 */
@Service
public class VaccinationCenterService {
    private List<VaccinationCenter> vaccinationCenterList = new ArrayList();

    public VaccinationCenterService() {
    }

    public void createVaccinationCenter(String code, String address) {
        this.vaccinationCenterList.add(new VaccinationCenter(code, address));
    }

    //Creates a vaccination list
    public String createVaccinationCenter(VaccinationCenter center) {
        this.vaccinationCenterList.add(center);
        try {
            FileParser.writeAll(this.getAllAsObjects());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "created successfully!";
    }

    public List<VaccinationCenter> getAllCenters() {
        return this.vaccinationCenterList;
    }


    //This method prints all the timeslot of the center with code s
    public void printTimeslotsOfCenter(String s) {
        for (VaccinationCenter center : vaccinationCenterList) {
            if (center.getCode().equals(s)) {
                System.out.println("Timeslots of Vaccination Center with code: " + center.getCode() + ":");
                center.getTimeslots().forEach(System.out::println);
            }
        }
    }


    //printFreeTimeslots prints all the available timeslots of the center with code s
    public List<String> printFreeTimeslots(String s) {
        List<String> results = new ArrayList<>();
        for (VaccinationCenter center : vaccinationCenterList) {
            if (center.getCode().equals(s)) {
                results.add("The available timeslots of Center with code " +center.getCode() + ":");
                System.out.println(results.get(0));
                for (Timeslot timeslot: center.getTimeslots()) {
                    if (timeslot.isFree()) {
                        System.out.println(timeslot);
                        results.add(timeslot.toString());
                    }
                }
            }
        }
        return results;
    }


    //This method returns the vaccination center with code s
    public VaccinationCenter getCenterByCode(String s) {
        for (VaccinationCenter vaccinationCenter:vaccinationCenterList) {
            if (vaccinationCenter.getCode().equals(s))
                return vaccinationCenter;

        }
        return null;
    }


    //This method returns the vaccination center with the specific timeslot

    public VaccinationCenter getCenterByTimeslot(Timeslot timeslot) {
        for (VaccinationCenter vaccinationCenter:vaccinationCenterList) {
            for (Timeslot t:vaccinationCenter.getTimeslots()) {
                if (t.equals(timeslot))
                    return vaccinationCenter;
            }
        }
        return null;
    }


    //This method deletes the center by code
    public String deleteCenterByCode(String code) {
        if (vaccinationCenterList.removeIf(vaccinationCenter -> vaccinationCenter.getCode().equals(code))) {
            try {
                FileParser.writeAll(this.getAllAsObjects());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "deleted successfully!";
        }
        else {
            return "Center doesn't exist";
        }
    }

    public void setVaccinationCenterList(List<VaccinationCenter> list) {
        vaccinationCenterList = list;
    }

    public List<Object> getAllAsObjects() {

        List<Object> list = new ArrayList<>();
        list.addAll(vaccinationCenterList);
        return list;
    }


    //Assigns a timeslot to a certain doctor using the doctors amka and the timeslot's id
    public String assignTimeslotToCenter(String centerCode, int timeslotId) {
        TimeslotService timeslotService = new TimeslotService();
        Timeslot timeslot = timeslotService.getTimeslotbyID(timeslotId);
        VaccinationCenter vaccinationCenter = this.getCenterByCode(centerCode);
        if (timeslot == null || vaccinationCenter == null) {
            return "Wrong arguments! Try again.";
        }
        else {
                vaccinationCenter.addTimeslot(timeslot);
        }
        return  "Done!";
    }
}
