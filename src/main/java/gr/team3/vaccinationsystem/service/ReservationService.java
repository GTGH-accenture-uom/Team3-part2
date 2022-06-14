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

    public String createReservation(Insured insured, Timeslot timeslot, VaccinationCenter center) {
//        System.out.println(insured);
//        System.out.println(timeslot);
//        System.out.println(center);
        Pattern amkaPattern = Pattern.compile("\\d{11}");
        if (insured != null && amkaPattern.matcher(insured.getAmka()).matches() && timeslot.isFree()) {
            reservationList.add(new Reservation(insured, timeslot.getDoctor(), timeslot, center));
            timeslot.setFree(false);
            insured.increaseResCount();
            return "reservation created!";
        }else{
            return "cannot make reservation!";
        }

    }


    public void deleteReservation(Insured insured) {

        Reservation reservation = this.getReservationByAmka(insured.getAmka());
        reservation.getTimeslot().setFree(true);
        reservationList.remove(reservation);
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

    public String changeReservation(Insured insured) {
            deleteReservation(insured);
            return "Deleted successfully";
    }

    public Reservation getReservationByAmka(String amka) {
        for (Reservation reservation : reservationList) {
            if (reservation.getInsuredPerson().getAmka().equals(amka)) {
                return reservation;
            }
        }
        return null;
    }

    public List<Reservation> getReservations() {
        return reservationList;
    }

    public Reservation getReservationByAmkaAndTimeslot(String amka, Timeslot timeslot) {
        for (Reservation res:reservationList) {
            if (this.getReservationByAmka(amka).equals(res) && res.getTimeslot().equals(timeslot))
                return res;
        }
        return null;
    }




    public List<String> getFutureReservationList() {
        List<Reservation> futureReservation = new ArrayList<>();
        for(Reservation reservation : reservationList){
            if(!reservation.getDone()){
                if(!reservation.getTimeslot().getLocalDate().isBefore(LocalDate.now())){
                    futureReservation.add(reservation);
                }
            }
        }
        List<String> customReservations = new ArrayList<>();
        for(Reservation reservation: futureReservation){
            customReservations.add(reservation.getData());
        }
        return customReservations;
    }



    public List<String> getReservationListByDay(int day, int month, int year) {
        List<Reservation> reservationByDay = new ArrayList<>();
        for(Reservation reservation : reservationList){
                if((reservation.getTimeslot().getDay() == day) &&  (reservation.getTimeslot().getMonth() == month) &&  (reservation.getTimeslot().getYear() == year )){
                    reservationByDay.add(reservation);
                }
        }
        List<String> customReservations = new ArrayList<>();
        for(Reservation reservation: reservationByDay){
            customReservations.add(reservation.getData());
        }
        return customReservations;
    }

}


