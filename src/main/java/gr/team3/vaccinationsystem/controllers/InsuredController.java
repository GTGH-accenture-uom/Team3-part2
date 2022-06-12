package gr.team3.vaccinationsystem.controllers;

import gr.team3.vaccinationsystem.model.Insured;
import gr.team3.vaccinationsystem.service.InsuredService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsuredController {

    //Check if your vaccination is valid, and it's expiration date
    @GetMapping("/checkIfYouHaveCoverage")
    public String checkIfYouHaveCoverage(@RequestParam() String amka) {
        Insured insured = InsuredService.getInsuredByAmka(amka);
        if (!insured.getAmka().equals(amka))
        {
            return "This amka doesn't exist";
        }
        else  return InsuredService.checkHasCoverage(insured);
    }
}
