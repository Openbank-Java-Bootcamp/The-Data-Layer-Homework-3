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
            clearConsole();
            input = inputAutowired.promptIntWithValidation("(1) New Sales Rep \n(2) Show all Sales Reps " +
                    "\n\n(99) Go Back", 99);

            if (input == 1) createNewSalesRep();
            else if (input == 2) showAllSalesRep();
        }
    }


    public void createNewSalesRep() {

        clearConsole();
        printHeading("Please input the following Sales Rep information");

        String newSalesRepName = inputAutowired.promptTextWithValidation("Insert the SalesRep " +
                "name", List.of(Validation.NAME));

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
                System.out.println(salesRep.toString());
            }

            String salesRepId = inputAutowired.promptTextWithValidation("\n\nInsert the Sales Rep ID\n", List.of(Validation.SALESREP));

            selectedSalesRep = salesRepRepository.findById(salesRepId).get();

        } else {
            Utils.printLikeError("No Sales reps in the database, please create one");
        }
        return selectedSalesRep;
    }


}
