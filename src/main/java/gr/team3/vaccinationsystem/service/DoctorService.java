package gr.team3.vaccinationsystem.service;


import gr.team3.vaccinationsystem.FileParser;
import gr.team3.vaccinationsystem.model.Doctor;
import gr.team3.vaccinationsystem.model.Timeslot;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
This class represents the service for all the doctors.
It contains a list of all the doctors, and some needed methods
such as the creation of a doctor.
 */
@Service
public class DoctorService {

    private static List<Doctor> doctorsList = new ArrayList<>();


    public String createDoctor(String amka, String name, String surname){
        for (Doctor doctor:doctorsList){
            if (doctor.getAmka().equals(amka))
                return "Dr with this amka already exists!";
        }
        doctorsList.add(new Doctor(amka,name,surname));
        try {
            FileParser.writeAll(this.getAllDoctorsAsObjects());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Doctor created!";
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
            try {
                FileParser.writeAll(this.getAllDoctorsAsObjects());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "Doctor with the: " + amka + " amka successfully deleted";
        }
    }


    //Gets doctor by amka
    public  Doctor getDoctorByAmka(String amka) {
        for (Doctor doctor:doctorsList) {
            if ( doctor.getSurname().equals(amka))
                return doctor;
        }
        return null;
    }


    //Shows doctor by name
    public List<Doctor> getDoctorByName(String name) {
        List<Doctor> doctors = new ArrayList<>();
        for (Doctor doctor : doctorsList) {
            if ( doctor.getSurname().equals(name))
                doctors.add(doctor);
        }
        return doctors;
    }


    //Shows doctor by surname
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


    //Assigns a timeslot to a certain doctor using the doctors amka and the timeslot's id
    public String assignTimeslotToDoctorByAmka(String amka, int id) {
        TimeslotService timeslotService = new TimeslotService();
        Timeslot timeslot = timeslotService.getTimeslotbyID(id);
        Doctor doctor = this.getDoctorByAmka(amka);
        if (timeslot == null || doctor == null) {
            return "Wrong arguments! Try again.";
        }
        else {
            if (timeslot.getDoctor() != null) {
                return "This timeslot already has a doctor assigned";
            }else {
                timeslot.setDoctor(doctor);
            }
        }
        return  "Done!";
    }


}

