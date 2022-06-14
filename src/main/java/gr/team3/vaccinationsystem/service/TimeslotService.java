package gr.team3.vaccinationsystem.service;

import gr.team3.vaccinationsystem.model.Doctor;
import gr.team3.vaccinationsystem.model.Insured;
import gr.team3.vaccinationsystem.model.Timeslot;
import org.springframework.stereotype.Service;

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

    public static void setTimeslotList(List<Timeslot> timeslotList) {
        TimeslotService.timeslotList = timeslotList;
    }

    //This is a method that gets all the free timeslots by day, month and year
    public  List<Timeslot> getFreeTimeslots(int day, int month, int year) {
        List<Timeslot> freeTimeslots = new ArrayList<>();
        for (Timeslot timeslot: timeslotList) {
            if ((timeslot.getDay() == day) &&  (timeslot.getMonth() == month) &&  (timeslot.getYear() == year && timeslot.isFree())) {
                freeTimeslots.add(timeslot);
            }
        }
        return freeTimeslots;
    }



    //This is a method that gets all the free timeslots by month and year
    public  List<Timeslot> getFreeTimeslotsByMonth(int month, int year) {
        List<Timeslot> freeTimeslots = new ArrayList<>();
        for (Timeslot timeslot: timeslotList) {
            if ((timeslot.getMonth() == month) &&  (timeslot.getYear() == year) && timeslot.isFree()) {
                freeTimeslots.add(timeslot);
            }
        }
        return freeTimeslots;
    }

    //This method adds all timeslots in the list
    public String addTimeslot(Timeslot timeslot){
        if (timeslot.getLocalDate().isBefore(LocalDate.now()))
                return "Invalid date";
        timeslot.setID(ID++);
        timeslotList.add(timeslot);
        return "timeslot created successfully!";
    }

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

    public Timeslot getTimeslotByDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        for (Timeslot timeslot:timeslotList) {
            if (timeslot.getLocalDateWithTime().equals(dateTime))
                return timeslot;
        }
        return null;
    }

    public Timeslot getTimeslotbyID(int ID) {
        for (Timeslot t: timeslotList) {
            if (t.getID().equals(ID))
                return t;

        }
        return null;
    }


    public String deleteTimeslotByID(Integer id) {
        if (timeslotList.removeIf(timeslot -> timeslot.getID().equals(id))) {
            return "deleted successfully!";
        }
        else {
            return "timeslot doesn't exist";
        }
    }


}
