package gr.team3.vaccinationsystem.service;


import gr.team3.vaccinationsystem.model.Doctor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
This class represents the service for all the doctors.
It contains a list of all the doctors, and some needed methods
such as the creation of a doctor.
 */
@Service
public class DoctorService {

    private List<Doctor> doctorsList = new ArrayList<>();

    public void createDoctor(String amka, String name, String surname){
        doctorsList.add(new Doctor(amka,name,surname));
    }

    public List<Doctor> getAllDoctors(){
        return doctorsList;
    }

    public Doctor getDoctorByNameSurname(List<String> name_surname) {
        for (Doctor doctor:doctorsList) {
            if (doctor.getName().equals(name_surname.get(0)) && doctor.getSurname().equals(name_surname.get(1)))
                return doctor;
        }
        return null;
    }
}
