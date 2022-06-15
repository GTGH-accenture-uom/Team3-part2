package gr.team3.vaccinationsystem.controllers;

import gr.team3.vaccinationsystem.model.Doctor;
import gr.team3.vaccinationsystem.model.Insured;
import gr.team3.vaccinationsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    DoctorService doctorService = new DoctorService();

    //Creates a new insured doctor
    //http//localhost:8181/createDoctor?
    @PostMapping(path = "/createDoctor")
    public String createDoctor(@RequestBody Doctor doctor){
        doctorService.createDoctor(doctor.getAmka(),doctor.getName(),doctor.getSurname());
        return "Done!";
    }

    //Get the list of all the doctors
    //http//localhost:8181/doctorsList
    @GetMapping(path = "/doctorsList")
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    //Delete a doctor
    //http//localhost:8181/deleteDoctor?amka=084978592
    @PostMapping(path = "/deleteDoctor")
    public String deleteDoctorByAmka(@RequestParam(name = "amka") String amka){
        return doctorService.deleteDoctorByAmka(amka);
    }


    //Show a doctor by amka
    //http//localhost:8181/showDoctor?amka=084978592
    @GetMapping(path = "/showDoctorByAmka")
    public Doctor showDoctorByAmka(@RequestParam(name = "amka") String amka){
        return doctorService.getDoctorByAmka(amka);
    }


    //Show a doctor by name
    //http//localhost:8181/showDoctorByName?name=Ioannis
    @GetMapping(path = "/showDoctorByName")
    public List<Doctor> showDoctorByName(@RequestParam(name = "name") String name){
        return doctorService.getDoctorByName(name);
    }


    //Show a doctor by surname
    //http//localhost:8181/showDoctorBySurname?name=Petrou
    @GetMapping(path = "/showDoctorBySurname")
    public List<Doctor> showDoctorBySurname(@RequestParam(name = "surname") String surname){
        return doctorService.showDoctorBySurname(surname);
    }


}
