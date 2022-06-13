package gr.team3.vaccinationsystem.controllers;

import gr.team3.vaccinationsystem.model.Insured;
import gr.team3.vaccinationsystem.service.InsuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsuredController {
    @Autowired
    InsuredService insuredService = new InsuredService();
    //Check if your vaccination is valid, and it's expiration date
    @GetMapping("/checkIfYouHaveCoverage")
    public String checkIfYouHaveCoverage(@RequestParam() String amka) {
        System.out.println(insuredService.getAllInsured());
        Insured insured = insuredService.getInsuredByAmka(amka);
        if (insured == null)
        {
            return "This amka doesn't exist";
        }
        else  return insuredService.checkHasCoverage(insured);
    }
}
