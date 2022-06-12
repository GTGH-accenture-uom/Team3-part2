package gr.team3.vaccinationsystem.service;

import gr.team3.vaccinationsystem.model.Insured;
import gr.team3.vaccinationsystem.model.Timeslot;

import java.util.ArrayList;
import java.util.List;

/*
This class represents the service for all the timeslots.
It contains a list of all the timeslots, and some needed methods
such a method to add all timeslots in the list.
 */
public class TimeslotService {

    private static List<Timeslot> timeslotList = new ArrayList<>();

    public static List<Timeslot> getTimeslotList() {
        return timeslotList;
    }

    public static void setTimeslotList(List<Timeslot> timeslotList) {
        TimeslotService.timeslotList = timeslotList;
    }

    //This is a method that gets all the free timeslots by day, month and year
    public static List<Timeslot> getFreeTimeslotsByDayByMonthByYear(int day, int month, int year) {
        List<Timeslot> freeTimeslots = new ArrayList<>();
        for (Timeslot timeslot: timeslotList) {
            if ( (Timeslot.getDay(day) == day) &&  (Timeslot.getMonth(month) == month) &&  (Timeslot.getYear(year) == year))
            {
                if ( timeslot.isFree()) {
                    freeTimeslots.add(timeslot);
                    break;
                }
            }
        }
        return freeTimeslots;
    }
    //This is a method that gets all the free timeslots by month and year
    public static List<Timeslot> getFreeTimeslotsByMonthByYear(int month, int year) {
        List<Timeslot> freeTimeslots = new ArrayList<>();
        for (Timeslot timeslot: timeslotList) {
            if ((Timeslot.getMonth(month) == month) &&  (Timeslot.getYear(year) == year))
            {
                if ( timeslot.isFree()) {
                    freeTimeslots.add(timeslot);
                    break;
                }
            }
        }
        return freeTimeslots;
    }

    //This method adds all timeslots in the list
    public void addTimeslot(Timeslot timeslot){
        timeslotList.add(timeslot);
    }
}
