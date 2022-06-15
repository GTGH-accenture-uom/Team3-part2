package gr.team3.vaccinationsystem;

import gr.team3.vaccinationsystem.model.*;
import gr.team3.vaccinationsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gr.team3.vaccinationsystem.model.Timeslot;

import java.lang.management.GarbageCollectorMXBean;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class AppConfig {

    @Autowired
    VaccinationService vaccinationService = new VaccinationService();

    @Autowired
    InsuredService insuredService = new InsuredService();

    @Autowired
    VaccinationCenterService vaccinationCenterService = new VaccinationCenterService();

    @Autowired
    DoctorService doctorService = new DoctorService();

    @Autowired
    TimeslotService timeslotService = new TimeslotService();

    @Autowired
    ReservationService reservationService = new ReservationService();


    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
//            insuredService.createInsured("162207556", "18029704689", "Konstantinos", "Antoniadis", LocalDate.parse("1997-02-18"), "kon.anton@gmail.com");
//            insuredService.createInsured("108204866", "24121101368", "Euaggelia", "Ilioudi", LocalDate.parse("2011-12-24"), "eua.iliou@gmail.com");
//            insuredService.createInsured("503307568", "28083504789", "Stefanos", "Papakonstantinou", LocalDate.parse("1935-08-28"), "stef.pap@gmail.com");
//            insuredService.createInsured("325687147", "01074807816", "Ioannis", "Petrakis", LocalDate.parse("1948-07-01"), "ion.petr@gmail.com");
//            insuredService.createInsured("481590296", "19036504689", "Dimitrios", "Kasidas", LocalDate.parse("1965-03-19"), "dim.kas@gmail.com");
//            insuredService.createInsured("648237666", "28090801369", "Eleni", "Vlahodimou", LocalDate.parse("2008-09-28"), "el.vlah@gmail.com");
//            insuredService.createInsured("279600359", "05037604637", "Chara", "Kalogeropoulou", LocalDate.parse("1976-03-05"), "chara.kaloger@gmail.com");
//            insuredService.createInsured("412348532", "24010604689", "Athanasios", "Kokkinos", LocalDate.parse("2006-01-24"), "athan.kok@gmail.com");
//            insuredService.createInsured("784206336", "22024204689", "Katerina", "Konstantinidh", LocalDate.parse("1942-02-22"), "kat.konst@gmail.com");
//            insuredService.createInsured("456202576", "04118801486", "Dimitra", "Gkanidi", LocalDate.parse("1988-11-04"), "dim.gkan@gmail.com");
//            insuredService.createInsured("783001492", "28080306644", "Nikoleta", "Apostolou", LocalDate.parse("2003-08-28"), "nikol.ap@gmail.com");
//            insuredService.createInsured("842124577", "14070004788", "Antonios", "Nikolaou", LocalDate.parse("2000-07-14"), "anton.nikol@gmail.com");
//            insuredService.createInsured("185207410", "29019504689", "Maria", "Panagiotidou", LocalDate.parse("1995-01-29"), "mar.panag@gmail.com");
//            insuredService.createInsured("664201431", "15064701124", "Vaios", "Xitsas", LocalDate.parse("1947-06-15"), "vag.xits@gmail.com");
//            insuredService.createInsured("501903374", "26048108643", "Panagiotis", "Karathanos", LocalDate.parse("1981-04-26"), "pan.karath@gmail.com");
//
//            //Create the Vaccination centers
//            vaccinationCenterService.createVaccinationCenter("01", "Konstantinoupolews 18");
//            vaccinationCenterService.createVaccinationCenter("02", "Ippokratio 20");
//
//            //Create the doctors
//
//            doctorService.createDoctor("28088501568", "Panagiotis", "Panagiotidis");
//            doctorService.createDoctor("01028400368", "Ioannis", "Petrou");
//            doctorService.createDoctor("16048009878", "Maria", "Mitsiou");
//            doctorService.createDoctor("23068601368", "Asterios", "Papadopoulos");
//
//
//            //Create the timeslots
//            timeslotService.addTimeslot(new Timeslot(6, 10, 2022, 18, 00, 01, 20, doctorService.getAllDoctors().get(0)));
//            timeslotService.addTimeslot(new Timeslot(9, 11, 2022, 12, 10, 11, 30, doctorService.getAllDoctors().get(0)));
//            timeslotService.addTimeslot(new Timeslot(2, 7, 2022, 8, 05, 06, 26, doctorService.getAllDoctors().get(0)));
//            timeslotService.addTimeslot(new Timeslot(22, 8, 2022, 9, 15, 16, 30, doctorService.getAllDoctors().get(0)));
//            timeslotService.addTimeslot(new Timeslot(7, 9, 2022, 19, 20, 21, 40, doctorService.getAllDoctors().get(0)));
//            timeslotService.addTimeslot(new Timeslot(30, 8, 2022, 12, 50, 51, 10, doctorService.getAllDoctors().get(1)));
//            timeslotService.addTimeslot(new Timeslot(12, 8, 2022, 2, 00, 01, 15, doctorService.getAllDoctors().get(1)));
//            timeslotService.addTimeslot(new Timeslot(1, 9, 2022, 9, 30, 31, 50, doctorService.getAllDoctors().get(1)));
//            timeslotService.addTimeslot(new Timeslot(4, 12, 2022, 20, 10, 11, 30, doctorService.getAllDoctors().get(1)));
//            timeslotService.addTimeslot(new Timeslot(10, 10, 2022, 10, 20, 21, 40, doctorService.getAllDoctors().get(1)));
//
//            timeslotService.addTimeslot(new Timeslot(7, 9, 2022, 18, 00, 01, 20, doctorService.getAllDoctors().get(2)));
//            timeslotService.addTimeslot(new Timeslot(10, 7, 2022, 12, 10, 11, 30, doctorService.getAllDoctors().get(2)));
//            timeslotService.addTimeslot(new Timeslot(3, 9, 2022, 8, 05, 06, 26, doctorService.getAllDoctors().get(2)));
//            timeslotService.addTimeslot(new Timeslot(23, 12, 2022, 9, 15, 16, 30, doctorService.getAllDoctors().get(2)));
//            timeslotService.addTimeslot(new Timeslot(8, 11, 2022, 19, 20, 21, 40, doctorService.getAllDoctors().get(2)));
//            timeslotService.addTimeslot(new Timeslot(29, 7, 2022, 12, 50, 51, 10, doctorService.getAllDoctors().get(3)));
//            timeslotService.addTimeslot(new Timeslot(13, 8, 2022, 2, 00, 01, 15, doctorService.getAllDoctors().get(3)));
//            timeslotService.addTimeslot(new Timeslot(2, 7, 2022, 9, 30, 31, 50, doctorService.getAllDoctors().get(3)));
//            timeslotService.addTimeslot(new Timeslot(5, 8, 2022, 20, 10, 11, 30, doctorService.getAllDoctors().get(3)));
//            timeslotService.addTimeslot(new Timeslot(11, 10, 2022, 10, 20, 21, 40, doctorService.getAllDoctors().get(3)));
//
//
//            //center1
//            vaccinationCenterService.getAllCenters().get(0).addTimeslot(timeslotService.getTimeslotList().get(0));
//            vaccinationCenterService.getAllCenters().get(0).addTimeslot(timeslotService.getTimeslotList().get(1));
//            vaccinationCenterService.getAllCenters().get(0).addTimeslot(timeslotService.getTimeslotList().get(2));
//            vaccinationCenterService.getAllCenters().get(0).addTimeslot(timeslotService.getTimeslotList().get(3));
//            vaccinationCenterService.getAllCenters().get(0).addTimeslot(timeslotService.getTimeslotList().get(4));
//            vaccinationCenterService.getAllCenters().get(0).addTimeslot(timeslotService.getTimeslotList().get(5));
//            vaccinationCenterService.getAllCenters().get(0).addTimeslot(timeslotService.getTimeslotList().get(6));
//            vaccinationCenterService.getAllCenters().get(0).addTimeslot(timeslotService.getTimeslotList().get(7));
//            vaccinationCenterService.getAllCenters().get(0).addTimeslot(timeslotService.getTimeslotList().get(8));
//            vaccinationCenterService.getAllCenters().get(0).addTimeslot(timeslotService.getTimeslotList().get(9));
//
//            //center2
//            vaccinationCenterService.getAllCenters().get(1).addTimeslot(timeslotService.getTimeslotList().get(10));
//            vaccinationCenterService.getAllCenters().get(1).addTimeslot(timeslotService.getTimeslotList().get(11));
//            vaccinationCenterService.getAllCenters().get(1).addTimeslot(timeslotService.getTimeslotList().get(12));
//            vaccinationCenterService.getAllCenters().get(1).addTimeslot(timeslotService.getTimeslotList().get(13));
//            vaccinationCenterService.getAllCenters().get(1).addTimeslot(timeslotService.getTimeslotList().get(14));
//            vaccinationCenterService.getAllCenters().get(1).addTimeslot(timeslotService.getTimeslotList().get(15));
//            vaccinationCenterService.getAllCenters().get(1).addTimeslot(timeslotService.getTimeslotList().get(16));
//            vaccinationCenterService.getAllCenters().get(1).addTimeslot(timeslotService.getTimeslotList().get(17));
//            vaccinationCenterService.getAllCenters().get(1).addTimeslot(timeslotService.getTimeslotList().get(18));
//            vaccinationCenterService.getAllCenters().get(1).addTimeslot(timeslotService.getTimeslotList().get(19));
//
//
////            //make reservations for 8 insured
//            reservationService.createReservation("18029704689",0);
//            reservationService.createReservation("24121101368",1);
//            reservationService.createReservation("28083504789",2);
//            reservationService.createReservation("29019504689",3);
//            reservationService.createReservation("04118801486",4);
//            reservationService.createReservation("15064701124",5);
//
//            //make vaccinations for 6 out of 8 reservations
////            vaccinationService.makeVaccination(reservationService.getReservationList().get(0));
////            vaccinationService.makeVaccination(reservationService.getReservationList().get(1));
////            vaccinationService.makeVaccination(reservationService.getReservationList().get(2));
////            vaccinationService.makeVaccination(ReservationService.getReservationList().get(3));
////            vaccinationService.makeVaccination(ReservationService.getReservationList().get(4));
////            vaccinationService.makeVaccination(ReservationService.getReservationList().get(5));
//
//            FileParser fileParser = new FileParser();
//
//            fileParser.writeAll(insuredService.getAllInsuredAsObjects());
//            fileParser.writeAll(doctorService.getAllDoctorsAsObjects());
//            fileParser.writeAll(reservationService.getAllResAsObjects());
//            fileParser.writeAll(timeslotService.getAllTimeslotsAsObjects());
//            //fileParser.writeAll(vaccinationService.getAllAsObjects());
//            fileParser.writeAll(vaccinationCenterService.getAllAsObjects());
//
            FileParser fileParser = new FileParser();
            List<Insured> list = fileParser.readInsured();
            insuredService.setInsuredList(list);
            for (Insured insured: insuredService.getAllInsured()) {
                System.out.println(insured);
            }

            List<Doctor> list2 = fileParser.readDoctors();
            doctorService.setDoctorList(list2);
            for (Doctor doctor: doctorService.getAllDoctors()) {
                System.out.println(doctor);
            }

            List<Reservation> res_list = fileParser.readReservations();
            reservationService.setReservationList(res_list);
            for (Reservation reservation: reservationService.getReservations()) {
                System.out.println(reservation);
            }

            List<Timeslot> timeslots = fileParser.readTimeslots();
            timeslotService.setTimeslotList(timeslots);
            for (Timeslot timeslot: timeslotService.getTimeslotList()) {
                System.out.println(timeslot);
            }

//            List<Vaccination> vaccinations = fileParser.readVaccinations();
//            vaccinationService.setVaccinationList(vaccinations);
//            for (Vaccination vaccination: vaccinationService.getVaccinationslist()) {
//                System.out.println(vaccination);
//            }

            List<VaccinationCenter> centers = fileParser.readVaccinationCenters();
            vaccinationCenterService.setVaccinationCenterList(centers);
            for (VaccinationCenter center: vaccinationCenterService.getAllCenters()) {
                System.out.println(center);
            }





        };

    }

}
