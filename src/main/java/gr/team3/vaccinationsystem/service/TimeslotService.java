package org.example.service;

import org.example.model.Timeslot;

import java.sql.Time;
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

    //This method adds all timeslots in the list
    public void addTimeslot(Timeslot timeslot){
        timeslotList.add(timeslot);
    }
}
