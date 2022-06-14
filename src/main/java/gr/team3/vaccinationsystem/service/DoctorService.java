package gr.team3.vaccinationsystem.service;


import gr.team3.vaccinationsystem.model.Doctor;
import org.springframework.stereotype.Service;

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

    //Deletes a doctor that already exists using the doctor's amka
    public String deleteDoctorByAmka(String amka) {
        Doctor doctor = this.getDoctorByAmka(amka);
        if (doctor == null) {
            return "The doctor with the given amka doesn't exist";
        }
        else
        {
           doctorsList.remove(doctor);
            return "Doctor with the: " + amka + " amka successfully deleted";
        }
    }


    public Doctor getDoctorByAmka(String amka) {
        for (Doctor doctor:doctorsList) {
            if ( doctor.getSurname().equals(amka))
                return doctor;
        }
        return null;
    }

    public List<Doctor> getDoctorByName(String name) {
        List<Doctor> doctors = new ArrayList<>();
        for (Doctor doctor : doctorsList) {
            if ( doctor.getSurname().equals(name))
                doctors.add(doctor);
        }
        return doctors;
    }

    public List<Doctor> showDoctorBySurname(String surname) {
        List<Doctor> doctors = new ArrayList<>();
        for (Doctor doctor : doctorsList) {
            if ( doctor.getSurname().equals(surname))
                doctors.add(doctor);
        }
        return doctors;
    }

    public void setDoctorList(List<Doctor> list) {
        doctorsList = list;
    }

    public List<Object> getAllDoctorsAsObjects() {
        List<Object> list = new ArrayList<>();
        list.addAll(doctorsList);
        return list;
    }
}
