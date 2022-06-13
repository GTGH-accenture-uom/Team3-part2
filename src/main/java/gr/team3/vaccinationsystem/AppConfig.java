package gr.team3.vaccinationsystem;

import gr.team3.vaccinationsystem.model.Timeslot;
import gr.team3.vaccinationsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

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
//Creating all the insured civilians
            insuredService.createInsured("162207556", "18029704689", "Konstantinos", "Antoniadis", LocalDate.parse("1997-02-18"), "kon.anton@gmail.com");
            insuredService.createInsured("108204866", "24121101368", "Euaggelia", "Ilioudi", LocalDate.parse("2011-12-24"), "eua.iliou@gmail.com");
            insuredService.createInsured("503307568", "28083504789", "Stefanos", "Papakonstantinou", LocalDate.parse("1935-08-28"), "stef.pap@gmail.com");
            insuredService.createInsured("325687147", "01074807816", "Ioannis", "Petrakis", LocalDate.parse("1948-07-01"), "ion.petr@gmail.com");
            insuredService.createInsured("481590296", "19036504689", "Dimitrios", "Kasidas", LocalDate.parse("1965-03-19"), "dim.kas@gmail.com");
            insuredService.createInsured("648237666", "28090801369", "Eleni", "Vlahodimou", LocalDate.parse("2008-09-28"), "el.vlah@gmail.com");
            insuredService.createInsured("279600359", "05037604637", "Chara", "Kalogeropoulou", LocalDate.parse("1976-03-05"), "chara.kaloger@gmail.com");
            insuredService.createInsured("412348532", "24010604689", "Athanasios", "Kokkinos", LocalDate.parse("2006-01-24"), "athan.kok@gmail.com");
            insuredService.createInsured("784206336", "22024204689", "Katerina", "Konstantinidh", LocalDate.parse("1942-02-22"), "kat.konst@gmail.com");
            insuredService.createInsured("456202576", "04118801486", "Dimitra", "Gkanidi", LocalDate.parse("1988-11-04"), "dim.gkan@gmail.com");
            insuredService.createInsured("783001492", "28080306644", "Nikoleta", "Apostolou", LocalDate.parse("2003-08-28"), "nikol.ap@gmail.com");
            insuredService.createInsured("842124577", "14070004788", "Antonios", "Nikolaou", LocalDate.parse("2000-07-14"), "anton.nikol@gmail.com");
            insuredService.createInsured("185207410", "29019504689", "Maria", "Panagiotidou", LocalDate.parse("1995-01-29"), "mar.panag@gmail.com");
            insuredService.createInsured("664201431", "15064701124", "Vaios", "Xitsas", LocalDate.parse("1947-06-15"), "vag.xits@gmail.com");
            insuredService.createInsured("501903374", "26048108643", "Panagiotis", "Karathanos", LocalDate.parse("1981-04-26"), "pan.karath@gmail.com");

            //Create the Vaccination centers
            vaccinationCenterService.createVaccinationCenter("01", "Konstantinoupolews 18");
            vaccinationCenterService.createVaccinationCenter("02", "Ippokratio 20");

            //Create the doctors

            doctorService.createDoctor("28088501568", "Panagiotis", "Panagiotidis");
            doctorService.createDoctor("01028400368", "Ioannis", "Petrou");
            doctorService.createDoctor("16048009878", "Maria", "Mitsiou");
            doctorService.createDoctor("23068601368", "Asterios", "Papadopoulos");


            //Create the timeslots
            timeslotService.addTimeslot(new Timeslot(6, 5, 2022, 18, 00, 01, 20, doctorService.getAllDoctors().get(0)));
            timeslotService.addTimeslot(new Timeslot(9, 4, 2022, 12, 10, 11, 30, doctorService.getAllDoctors().get(0)));
            timeslotService.addTimeslot(new Timeslot(2, 3, 2022, 8, 05, 06, 26, doctorService.getAllDoctors().get(0)));
            timeslotService.addTimeslot(new Timeslot(22, 12, 2021, 9, 15, 16, 30, doctorService.getAllDoctors().get(0)));
            timeslotService.addTimeslot(new Timeslot(7, 11, 2021, 19, 20, 21, 40, doctorService.getAllDoctors().get(0)));
            timeslotService.addTimeslot(new Timeslot(30, 5, 2022, 12, 50, 51, 10, doctorService.getAllDoctors().get(1)));
            timeslotService.addTimeslot(new Timeslot(12, 6, 2022, 2, 00, 01, 15, doctorService.getAllDoctors().get(1)));
            timeslotService.addTimeslot(new Timeslot(1, 7, 2021, 9, 30, 31, 50, doctorService.getAllDoctors().get(1)));
            timeslotService.addTimeslot(new Timeslot(4, 8, 2021, 20, 10, 11, 30, doctorService.getAllDoctors().get(1)));
            timeslotService.addTimeslot(new Timeslot(10, 10, 2021, 10, 20, 21, 40, doctorService.getAllDoctors().get(1)));

            timeslotService.addTimeslot(new Timeslot(7, 5, 2022, 18, 00, 01, 20, doctorService.getAllDoctors().get(2)));
            timeslotService.addTimeslot(new Timeslot(10, 4, 2022, 12, 10, 11, 30, doctorService.getAllDoctors().get(2)));
            timeslotService.addTimeslot(new Timeslot(3, 3, 2022, 8, 05, 06, 26, doctorService.getAllDoctors().get(2)));
            timeslotService.addTimeslot(new Timeslot(23, 12, 2021, 9, 15, 16, 30, doctorService.getAllDoctors().get(2)));
            timeslotService.addTimeslot(new Timeslot(8, 11, 2021, 19, 20, 21, 40, doctorService.getAllDoctors().get(2)));
            timeslotService.addTimeslot(new Timeslot(29, 5, 2022, 12, 50, 51, 10, doctorService.getAllDoctors().get(3)));
            timeslotService.addTimeslot(new Timeslot(13, 6, 2022, 2, 00, 01, 15, doctorService.getAllDoctors().get(3)));
            timeslotService.addTimeslot(new Timeslot(2, 7, 2021, 9, 30, 31, 50, doctorService.getAllDoctors().get(3)));
            timeslotService.addTimeslot(new Timeslot(5, 8, 2021, 20, 10, 11, 30, doctorService.getAllDoctors().get(3)));
            timeslotService.addTimeslot(new Timeslot(11, 10, 2021, 10, 20, 21, 40, doctorService.getAllDoctors().get(3)));


            //center1
            vaccinationCenterService.getAllCenters().get(0).addTimeslot(TimeslotService.getTimeslotList().get(0));
            vaccinationCenterService.getAllCenters().get(0).addTimeslot(TimeslotService.getTimeslotList().get(1));
            vaccinationCenterService.getAllCenters().get(0).addTimeslot(TimeslotService.getTimeslotList().get(2));
            vaccinationCenterService.getAllCenters().get(0).addTimeslot(TimeslotService.getTimeslotList().get(3));
            vaccinationCenterService.getAllCenters().get(0).addTimeslot(TimeslotService.getTimeslotList().get(4));
            vaccinationCenterService.getAllCenters().get(0).addTimeslot(TimeslotService.getTimeslotList().get(5));
            vaccinationCenterService.getAllCenters().get(0).addTimeslot(TimeslotService.getTimeslotList().get(6));
            vaccinationCenterService.getAllCenters().get(0).addTimeslot(TimeslotService.getTimeslotList().get(7));
            vaccinationCenterService.getAllCenters().get(0).addTimeslot(TimeslotService.getTimeslotList().get(8));
            vaccinationCenterService.getAllCenters().get(0).addTimeslot(TimeslotService.getTimeslotList().get(9));

            //center2
            vaccinationCenterService.getAllCenters().get(1).addTimeslot(TimeslotService.getTimeslotList().get(10));
            vaccinationCenterService.getAllCenters().get(1).addTimeslot(TimeslotService.getTimeslotList().get(11));
            vaccinationCenterService.getAllCenters().get(1).addTimeslot(TimeslotService.getTimeslotList().get(12));
            vaccinationCenterService.getAllCenters().get(1).addTimeslot(TimeslotService.getTimeslotList().get(13));
            vaccinationCenterService.getAllCenters().get(1).addTimeslot(TimeslotService.getTimeslotList().get(14));
            vaccinationCenterService.getAllCenters().get(1).addTimeslot(TimeslotService.getTimeslotList().get(15));
            vaccinationCenterService.getAllCenters().get(1).addTimeslot(TimeslotService.getTimeslotList().get(16));
            vaccinationCenterService.getAllCenters().get(1).addTimeslot(TimeslotService.getTimeslotList().get(17));
            vaccinationCenterService.getAllCenters().get(1).addTimeslot(TimeslotService.getTimeslotList().get(18));
            vaccinationCenterService.getAllCenters().get(1).addTimeslot(TimeslotService.getTimeslotList().get(19));


            //make reservations for 8 insured
            reservationService.createReservation(insuredService.getInsuredByAmka("18029704689"), TimeslotService.getTimeslotList().get(0), vaccinationCenterService.getCenterByTimeslot(TimeslotService.getTimeslotList().get(0)));
            reservationService.createReservation(insuredService.getInsuredByAmka("24121101368"), TimeslotService.getTimeslotList().get(1), vaccinationCenterService.getCenterByTimeslot(TimeslotService.getTimeslotList().get(1)));
            reservationService.createReservation(insuredService.getInsuredByAmka("28083504789"), TimeslotService.getTimeslotList().get(2), vaccinationCenterService.getCenterByTimeslot(TimeslotService.getTimeslotList().get(2)));
            reservationService.createReservation(insuredService.getInsuredByAmka("19036504689"), TimeslotService.getTimeslotList().get(3), vaccinationCenterService.getCenterByTimeslot(TimeslotService.getTimeslotList().get(3)));
            reservationService.createReservation(insuredService.getInsuredByAmka("29019504689"), TimeslotService.getTimeslotList().get(10), vaccinationCenterService.getCenterByTimeslot(TimeslotService.getTimeslotList().get(10)));
            reservationService.createReservation(insuredService.getInsuredByAmka("04118801486"), TimeslotService.getTimeslotList().get(11), vaccinationCenterService.getCenterByTimeslot(TimeslotService.getTimeslotList().get(11)));
            reservationService.createReservation(insuredService.getInsuredByAmka("15064701124"), TimeslotService.getTimeslotList().get(15), vaccinationCenterService.getCenterByTimeslot(TimeslotService.getTimeslotList().get(15)));
            reservationService.createReservation(insuredService.getInsuredByAmka("26048108643"), TimeslotService.getTimeslotList().get(18), vaccinationCenterService.getCenterByTimeslot(TimeslotService.getTimeslotList().get(18)));

            //make vaccinations for 6 out of 8 reservations
            vaccinationService.makeVaccination(ReservationService.getReservationList().get(0));
            vaccinationService.makeVaccination(ReservationService.getReservationList().get(1));
            vaccinationService.makeVaccination(ReservationService.getReservationList().get(2));
            vaccinationService.makeVaccination(ReservationService.getReservationList().get(3));
            vaccinationService.makeVaccination(ReservationService.getReservationList().get(4));
            vaccinationService.makeVaccination(ReservationService.getReservationList().get(5));

            timeslotService.printTimeslotsLocalDate();
           reservationService.getReservations().forEach(System.out::println);
        };

    }

}
