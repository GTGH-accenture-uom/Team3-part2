package gr.team3.vaccinationsystem.service;

import gr.team3.vaccinationsystem.FileParser;
import gr.team3.vaccinationsystem.model.Doctor;
import gr.team3.vaccinationsystem.model.Insured;
import gr.team3.vaccinationsystem.model.Timeslot;
import gr.team3.vaccinationsystem.model.VaccinationCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*
This class represents the service for all the timeslots.
It contains a list of all the timeslots, and some needed methods
such a method to add all timeslots in the list.
 */
@Service
public class TimeslotService {

    private static int ID;
    private static List<Timeslot> timeslotList = new ArrayList<>();


    public List<Timeslot> getTimeslotList() {
        return timeslotList;
    }

    public void setTimeslotList(List<Timeslot> list) {
        timeslotList = list;
    }


    //This is a method that gets all the free timeslots by day, month and year
    public  List<String> getFreeTimeslots(int day, int month, int year) {
        List<String> freeTimeslots = new ArrayList<>();
        for (Timeslot timeslot: timeslotList) {
            if ((timeslot.getDay() == day) &&  (timeslot.getMonth() == month) &&  (timeslot.getYear() == year && timeslot.isFree()) && timeslot.getDoctor()!=null) {
                freeTimeslots.add(timeslot.getData());            }
        }
        return freeTimeslots;
    }


    //This is a method that gets all the free timeslots by month and year
    public  List<String> getFreeTimeslotsByMonth(int month, int year) {
        List<String> freeTimeslots = new ArrayList<>();
        for (Timeslot timeslot: timeslotList) {
            if ((timeslot.getMonth() == month) &&  (timeslot.getYear() == year) && timeslot.isFree() && timeslot.getDoctor()!=null) {
                freeTimeslots.add(timeslot.getData());
            }
        }
        return freeTimeslots;
    }


    //This method adds all timeslots in the list
    public String addTimeslot(Timeslot timeslot){
        timeslot.setFree(true);
        if (timeslot.getLocalDate().isBefore(LocalDate.now()))
                return "Invalid date";

        if (timeslotList.size() == 0)
            timeslot.setID(0);
        else
            timeslot.setID((Integer) this.getTimeslotList().get(timeslotList.size()-1).getID()+1);
        timeslotList.add(timeslot);
        try {
            FileParser.writeAll(this.getAllTimeslotsAsObjects());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "timeslot created successfully!";
    }


    //This method gets all timeslots for each day
    public Timeslot getTimeslotByDate(LocalDate date) {
        for (Timeslot timeslot:timeslotList) {
            if (timeslot.getLocalDate().equals(date))
                return timeslot;
        }
        return null;
    }


    public void printTimeslotsLocalDate(){
        for (Timeslot t:timeslotList) {
            System.out.println(t.getLocalDateWithTime() + "Dr: " +  t.getDoctor());

        }
    }

    //This method gets all timeslots for each day and for a certain time
    public Timeslot getTimeslotByDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        for (Timeslot timeslot:timeslotList) {
            if (timeslot.getLocalDateWithTime().equals(dateTime))
                return timeslot;
        }
        return null;
    }


    //This method gets all timeslots by id
    public  Timeslot getTimeslotbyID(Integer ID) {
        for (Timeslot t: timeslotList) {
            if (t.getID().equals(ID))
                return t;

        }
        return null;
    }


    //This method deletes all timeslots by id
    public String deleteTimeslotByID(Integer id) {
        if (timeslotList.removeIf(timeslot -> timeslot.getID().equals(id))) {
            try {
                FileParser.writeAll(this.getAllTimeslotsAsObjects());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "deleted successfully!";
        }
        else {
            return "timeslot doesn't exist";
        }
    }


    public List<Object> getAllTimeslotsAsObjects() {
        List<Object> list = new ArrayList<>();
        list.addAll(timeslotList);
        return list;
    }


    public List<String> getCustomTimeslotList() {
        List<String> AllTimeslots = new ArrayList<>();
        VaccinationCenterService vaccinationCenterService = new VaccinationCenterService();
        for (Timeslot timeslot : timeslotList) {
            if (timeslot.getDoctor() != null) {
                AllTimeslots.add(timeslot.getData());
            }
            else {
                AllTimeslots.add(timeslot.getDataWithoutDoctor());
            }
        }
        return AllTimeslots;
    }
}
