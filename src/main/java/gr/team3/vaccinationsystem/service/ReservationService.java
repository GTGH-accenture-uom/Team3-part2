package gr.team3.vaccinationsystem.service;


import gr.team3.vaccinationsystem.FileParser;
import gr.team3.vaccinationsystem.model.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public void setReservationList(List<Reservation> reservations) {
        reservationList = reservations;
    }


    /* creates a new reservation that an insured person made to a certain timeslot and adds it
     * to the list. Sets the corresponding timeslot isFree field to false so that it appears
     * as unavailable/booked*/
    public String createReservation(String amka, Integer ID) {
        TimeslotService timeslotService = new TimeslotService();
        Insured insured = new InsuredService().getInsuredByAmka(amka);
        Timeslot timeslot = timeslotService.getTimeslotbyID(ID);
        if (timeslot.isFree()) {
            timeslot.setFree(false);
            reservationList.add(new Reservation(insured, timeslot.getDoctor(), timeslot, new VaccinationCenterService().getCenterByTimeslotID(timeslot.getID())));
            insured.increaseResCount();
            try {
                FileParser.writeAll(this.getAllResAsObjects());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "reservation created!";
        }else{
            return "This timeslot in not available!";
        }

    }


    //Deletes a reservation
    public void deleteReservation(Insured insured) {
        //this returns null
        Reservation reservation = this.getReservationByAmka(insured.getAmka());
        reservation.getTimeslot().setFree(true);
        reservationList.remove(reservation);
        try {
            FileParser.writeAll(this.getAllResAsObjects());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //Deletes a reservation that already exists using the insured's amka
    public String deleteReservationByAmka(String amka){
        for (Reservation reservation: reservationList){
            if (reservation.getInsuredPerson().getAmka().equals(amka)){
                reservation.getTimeslot().setFree(true);
                reservationList.remove(reservation);
                try {
                    FileParser.writeAll(this.getAllResAsObjects());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return "Deleted this reservation!";
            }else{
                return "There is no reservation for this amka!";
            }
        }
        return null;
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


    //Changes a reservation
    public String changeReservation(String amka) {
        InsuredService insuredService = new InsuredService();
        Insured insured = insuredService.getInsuredByAmka(amka);
        if (insured == null)
            return "insured with the given amka doesn't exist";
        else {
            if (insured.getCount() < 3) {
                deleteReservation(insured);

                return "Deleted successfully";
            } else return "You can't change your reservation again!";
        }
    }


    //Gets a reservation using the insured's amka
    public Reservation getReservationByAmka(String amka) {
        System.out.println(reservationList);
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


    //Gets a reservation using the insured's amka and the timeslot
    public Reservation getReservationByAmkaAndTimeslot(String amka, Timeslot timeslot) {
       Reservation t1 = this.getReservationByTimeslotID(timeslot.getID());
        System.out.println(t1);
       Reservation t2 = this.getReservationByAmka(amka);
        System.out.println(t2);
       if (t1 == null || t2 == null)
           return null;
       if (t1.equals(t2))
           return t1;
       return null;
    }

    //Gets a reservation using the timeslot's id
    private Reservation getReservationByTimeslotID(Integer ID) {
        for (Reservation reservation : reservationList) {
            if (reservation.getTimeslot().getID().equals(ID)) {
                return reservation;
            }
        }
        return null;
    }


    //Gets all the reservations to come
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


    //Gets the reservation list for each day
    public List<String> getReservationListByDay(int day, int month, int year) {
        List<Reservation> reservationByDay = new ArrayList<>();
        for(Reservation reservation : reservationList){
                if((reservation.getTimeslot().getDay() == day) &&  (reservation.getTimeslot().getMonth() == month) &&  (reservation.getTimeslot().getYear() == year )) {
                    reservationByDay.add(reservation);
                }
        }
        List<String> customReservations = new ArrayList<>();
        for(Reservation reservation: reservationByDay){
            customReservations.add(reservation.getData());
        }
        return customReservations;
    }


    public List<Object> getAllResAsObjects() {

        List<Object> list = new ArrayList<>();
        list.addAll(reservationList);
        return list;
    }

    public String checkAllData(String amka, Integer ID, String doctor_name, String surname) {
        InsuredService insuredService =new InsuredService();
        Insured insured = insuredService.getInsuredByAmka(amka);
        if (insured == null)
            return "insured with the given amka doesn't exist";
        if (insured.checkIfHasReservation())
            return "you already have a reservation";
        List<String> name_surname = new ArrayList<>();
        name_surname.add(doctor_name);
        name_surname.add(surname);
        DoctorService doctorService =  new DoctorService();
        Doctor doctor = doctorService.getDoctorByNameSurname(name_surname);
        if (doctor == null){
            return "there is no doctor with such name and surname";
        }
        TimeslotService timeslotService = new TimeslotService();
        Timeslot timeslot = timeslotService.getTimeslotbyID(ID);
        if (timeslot == null)
            return "there is not such timeslot";
        VaccinationCenterService vaccinationCenterService = new VaccinationCenterService();
        if (vaccinationCenterService.getCenterByTimeslotID(timeslot.getID()) == null)
            return "This timeslot does not belong to a center.";
        System.out.println(timeslot);
        if (!timeslot.checkifDoctorisInTimeslot(doctor))
            return  "this Doctor does not belong to the given Timeslot";
        return "";
    }

    public List<String> getCustomReservationList() {
        List<String> customList = new ArrayList<>();
        System.out.println(reservationList);
        for (Reservation reservation:reservationList) {
            customList.add(reservation.getData());
        }
        return customList;
    }

    public boolean checkIfHasReservation(String amka) {
        for (Reservation res:reservationList)
            if (res.getInsuredPerson().getAmka().equals(amka)){
                    return true;}
        return false;
    }
}


