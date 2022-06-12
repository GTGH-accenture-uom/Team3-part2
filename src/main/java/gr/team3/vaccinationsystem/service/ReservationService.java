package gr.team3.vaccinationsystem.service;


import gr.team3.vaccinationsystem.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/*
This class represents the service for all the reservations.
It contains a list of all the reservations, and some needed methods
such as the creation of a reservation.
 */
@Service
public class ReservationService {

    private static List<Reservation> reservationList = new ArrayList<>();

    public ReservationService() {
    }

    public static List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    /* creates a new reservation that an insured person made to a certain timeslot and adds it
     * to the list. Sets the corresponding timeslot isFree field to false so that it appears
     * as unavailable/booked*/

    public void createReservation(Insured insured, Timeslot timeslot, VaccinationCenter center) {

        Pattern amkaPattern = Pattern.compile("\\d{11}");
        if (insured != null && amkaPattern.matcher(insured.getAmka()).matches() && timeslot.isFree()) {
            reservationList.add(new Reservation(insured, timeslot.getDoctor(), timeslot, center));
            timeslot.setFree(false);
        } else {
            System.out.println("cannot make this reservasion");
        }

    }


    public void deleteReservation(Insured insured, Timeslot timeslot) {
        reservationList.removeIf(reservation -> reservation.getInsuredPerson().equals(insured));

    }


    /*
    This method returns the reservations made to a certain Vaccinations Center, based
    on  the code given as a parameter.
     */
    public List<String> printReservationsOfCenter(String s) {
        List<String> result = new ArrayList<>();
        result.add("The reservations of center with code " + s + ":");
        System.out.println(result.get(0));
        List<Reservation> reservationsOfCenter = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            if (reservation.getVaccinationCenter().getCode().equals(s)) {
                reservationsOfCenter.add(reservation);
                result.add(reservation.toString());
            }
        }
        reservationsOfCenter.forEach(System.out::println);
        return  result;
    }

    /*This method shows all the reservations that each doctor has in each center
     */
    public void showResOfDoctorByCenter(Doctor doctor, VaccinationCenter center) {
        System.out.println("----------------------------");
        ArrayList<Reservation> reservations = new ArrayList<>();
        System.out.println("Reservations of Dr " + doctor.getName() + " " + doctor.getSurname()
                + " of Center with code " + center.getCode());
        for (Reservation reservation : reservationList) {
            if (reservation.getDoctor().equals(doctor) &&
                    reservation.getVaccinationCenter().equals(center)) {
                reservations.add(reservation);
            }

        }
        reservations.forEach(System.out::println);
    }


    /*This method shows all the reservations that each doctor has every day
     */
    public void showResOfDoctorByDay(Doctor doctor, LocalDate date) {
        System.out.println("----------------------------");
        ArrayList<Reservation> reservations = new ArrayList<>();
        System.out.println("Reservations of Dr " + doctor.getName() + " " + doctor.getSurname()
                + " on " + date);
        for (Reservation reservation : reservationList) {
            if (reservation.getDoctor().equals(doctor) &&
                    reservation.getTimeslot().getLocalDate().equals(date)) {
                reservations.add(reservation);
            }

        }
        reservations.forEach(System.out::println);
    }

    public void changeReservation(Insured insured, Timeslot timeslot, VaccinationCenter center) {
        if (insured.getCount() < 2) {
            deleteReservation(insured, timeslot);
            createReservation(insured, timeslot, center);
            insured.setCount(insured.getCount() + 1);

        } else {
            System.out.println("You can't change the reservation again.");
        }

    }

    public Reservation getReservationByAmka(String amka) {
        for (Reservation reservation : reservationList) {
            if (reservation.getInsuredPerson().getAmka().equals(amka)) {
                return reservation;
            }
        }
        return null;
    }


//    public List<Reservation> getFutureReservationList() {
//        for(Reservation reservation : reservationList){
//            if(reservation.get)
//        }
//    }
}


