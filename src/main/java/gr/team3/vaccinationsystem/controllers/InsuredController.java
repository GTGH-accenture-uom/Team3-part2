package gr.team3.vaccinationsystem.controllers;

import gr.team3.vaccinationsystem.model.Doctor;
import gr.team3.vaccinationsystem.model.Insured;
import gr.team3.vaccinationsystem.service.InsuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InsuredController {
    @Autowired
    InsuredService insuredService = new InsuredService();


    //Creates a new insured person
    //http://localhost:8181/createInsured?afm=084978592&amka=948358798&name=Ioannis&surname=Petridis&Birthdate=1997-05-05&email=ioannis1997@ihu.com
    @PostMapping(path = "/createInsured")
    public String createInsured(@RequestBody Insured insured){
       insuredService.createInsured(insured.getAfm(),
                                    insured.getAmka(),
                                    insured.getName(),
                                    insured.getSurname(),
                                    insured.getBirthdate(),
                                    insured.getEmail());
        return "Done!";
    }


    //Get the list of all the insured
    //http://localhost:8181/insuredList
    @GetMapping(path = "/insuredList")
    public List<Insured> getInsured(){
        return insuredService.getAllInsured();
    }

    //Delete an insured
    //http://localhost:8181/deleteInsured?amka=084978592
    @DeleteMapping(path = "/deleteInsured")
    public String deleteInsuredByAmka(@RequestParam(name = "amka") String amka){
        return insuredService.deleteInsuredByAmka(amka);
    }


    //Show an insured by amka
    //http://localhost:8181/showInsured?amka=084978592
    @GetMapping(path = "/showInsured")
    public Insured showInsuredByAmka(@RequestParam(name = "amka") String amka){
        return insuredService.getInsuredByAmka(amka);
    }



    //Check if your vaccination is valid, and it's expiration date by inserting
    //your amka
    //http://localhost:8181/checkHasCoverage?amka=084978592
    @GetMapping(path = "/checkHasCoverage")
    public  String checkHasCoverage(@RequestParam(name = "amka") String amka) {
        return insuredService.checkHasCoverage(amka);
    }


}
