package com.ironhack.TheDataLayerHomework3.menu;


import com.ironhack.TheDataLayerHomework3.enums.Validation;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import com.ironhack.TheDataLayerHomework3.repository.LeadRepository;
import com.ironhack.TheDataLayerHomework3.repository.OpportunityRepository;
import com.ironhack.TheDataLayerHomework3.repository.SalesRepRepository;
import com.ironhack.TheDataLayerHomework3.utils.Input;
import com.ironhack.TheDataLayerHomework3.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ironhack.TheDataLayerHomework3.enums.Status.*;
import static com.ironhack.TheDataLayerHomework3.utils.Utils.clearConsole;
import static com.ironhack.TheDataLayerHomework3.utils.Utils.printHeading;

@Component
public class SalesRepMenu {

    //same as @Autowired SalesRepRepository
    final private SalesRepRepository salesRepRepository;
    final private LeadRepository leadRepository;

    final private OpportunityRepository opportunityRepository;
    final private Input inputAutowired;

    public SalesRepMenu(SalesRepRepository salesRepRepository, LeadRepository leadRepository, OpportunityRepository opportunityRepository, Input inputAutowired) {
        this.salesRepRepository = salesRepRepository;
        this.leadRepository = leadRepository;
        this.opportunityRepository = opportunityRepository;
        this.inputAutowired = inputAutowired;
    }

    public void menu() {
        int input = 0;

        while (input != 99) {

            input = inputAutowired.promptIntWithValidation("(1) New Sales Rep \n(2) Show all Sales Reps " +
                    "\n(3) Report Lead by SalesRep" + "\n(4) Report Opportunity by SalesRep" +
                    "\n(5) Report Opportunity Status by SalesRep" +
                    "\n(6) Report Opportunity by Product" +
                    "\n(7) Report Opportunity Status by Product" +
                    "\n(11) Report Opportunity Status by City" +
                    "\n(99) Go Back", 99);

            if (input == 1) createNewSalesRep();
            else if (input == 2) showAllSalesRep();
            else if (input == 3 ) reportLeadBySalesRep();
            else if (input == 5 ) reportOpportunityBySalesRepAndStatus();
            else if (input == 11 ) reportOpportunityByStatusAndCity();
        }
    }

    private void reportOpportunityByStatusAndCity() {
    }

    private void reportOpportunityBySalesRepAndStatus() {
             SalesRep salesRep = getSalesRepFromInputId();
             printHeading(" \n Choose the status for opportunity " + " \n ");
             System.out.println("(1) Report OPEN by SalesRep");
             System.out.println("(2) Report CLOSED-WON by SalesRep");
             System.out.println("(3) Report CLOSED-LOST by SalesRep");


                 Integer newInput = inputAutowired.promptIntWithValidation("-> ", 3);
                   if (newInput == 1) {
                       System.out.println(opportunityRepository.countBySalesRepAndStatus(salesRep, OPEN));
                    } else if (newInput == 2) {
                       System.out.println(opportunityRepository.countBySalesRepAndStatus(salesRep, CLOSED_WON));
                    } else if (newInput == 3) {
                       System.out.println(opportunityRepository.countBySalesRepAndStatus(salesRep, CLOSED_LOST));
                   }
    }


    public void createNewSalesRep() {

        clearConsole();
        printHeading("Please input the following Sales Rep information");

        String newSalesRepName = inputAutowired.promptTextWithValidation("Insert the SalesRep name", List.of(Validation.NAME));

        SalesRep currentSalesRep = new SalesRep(newSalesRepName);

        SalesRep storedSalesRep = salesRepRepository.save(currentSalesRep);

        System.out.println("Successfully created Sales Rep");
        System.out.println(storedSalesRep);

        Utils.anythingToContinue();
        Utils.clearConsole();
    }

    public void showAllSalesRep() {
        clearConsole();

        List<SalesRep> salesRepList = salesRepRepository.findAll();

        if (!salesRepList.isEmpty()) {
            Utils.printHeading("- Your current Sales Reps - ");

            for (SalesRep salesRep : salesRepList) {
                System.out.println(salesRep);
            }
        } else {
            Utils.printLikeError("No Sales Reps in the database, please create one");
        }
        Utils.anythingToContinue();
        Utils.clearConsole();
    }

    public SalesRep getSalesRepFromInputId() {
        SalesRep selectedSalesRep = null;
        var salesRepList = salesRepRepository.findAll();

        if (!salesRepList.isEmpty()) {
            for (SalesRep salesRep : salesRepList) {
                System.out.println("Sales Rep ID: " + salesRep.getId() + " Name: " + salesRep.getName());
            }

            String salesRepId = inputAutowired.promptTextWithValidation("Insert the Sales Rep ID", List.of(Validation.SALESREP));

            for (SalesRep salesRep : salesRepList) {
                if (salesRep.getId().equals(salesRepId)) selectedSalesRep = salesRep;
            }
        } else {
            Utils.printLikeError("No Sales reps in the database, please create one");
        }
        return selectedSalesRep;
    }


    public void reportLeadBySalesRep(){
        SalesRep salesRep = getSalesRepFromInputId();

        System.out.println(salesRep.getName()+"have "+ leadRepository.countBySalesRep(salesRep) + "lead");
    }

}
