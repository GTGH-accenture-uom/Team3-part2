package gr.team3.vaccinationsystem;

import gr.team3.vaccinationsystem.model.*;
import gr.team3.vaccinationsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.sql.Array;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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


//            fileParser.writeAll(insuredService.getAllInsuredAsObjects());
//            fileParser.writeAll(doctorService.getAllDoctorsAsObjects());
//            fileParser.writeAll(reservationService.getAllResAsObjects());
//            fileParser.writeAll(timeslotService.getAllTimeslotsAsObjects());
//            //fileParser.writeAll(vaccinationService.getAllAsObjects());
//            fileParser.writeAll(vaccinationCenterService.getAllAsObjects());


        };

    }

}
