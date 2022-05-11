package com.ironhack.TheDataLayerHomework3.menu;

import com.ironhack.TheDataLayerHomework3.enums.Validation;
import com.ironhack.TheDataLayerHomework3.models.Account;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import com.ironhack.TheDataLayerHomework3.repository.AccountRepository;
import com.ironhack.TheDataLayerHomework3.repository.LeadRepository;
import com.ironhack.TheDataLayerHomework3.repository.OpportunityRepository;
import com.ironhack.TheDataLayerHomework3.utils.Input;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ironhack.TheDataLayerHomework3.enums.Status.*;
import static com.ironhack.TheDataLayerHomework3.utils.Utils.*;

@Component
public class ReportMenu {

    final private Input inputAutowired;
    final private LeadRepository leadRepository;
    final private OpportunityRepository opportunityRepository;

    final private AccountRepository accountRepository;
    final private SalesRepMenu salesRepMenu;

    public ReportMenu(Input inputAutowired, LeadRepository leadRepository, OpportunityRepository opportunityRepository, AccountRepository accountRepository, SalesRepMenu salesRepMenu) {
        this.inputAutowired = inputAutowired;
        this.leadRepository = leadRepository;
        this.opportunityRepository = opportunityRepository;
        this.accountRepository = accountRepository;
        this.salesRepMenu = salesRepMenu;
    }

    public void menu() {
        int input = 0;

        while (input != 99) {
            clearConsole();
            input = inputAutowired.promptIntWithValidation(
                    "\n(3) Report Lead by SalesRep" +
                    "\n(4) Report Opportunity by SalesRep" +
                    "\n(5) Report Opportunity Status by SalesRep" +
                    "\n(6) Report Opportunity by Product" +
                    "\n(7) Report Opportunity Status by Product" +
                    "\n(11) Report Opportunity Status by City" +
                    "\n(99) Go Back", 99);


            if (input == 3 ) reportLeadBySalesRep();
            else if (input == 5 ) reportOpportunityBySalesRepAndStatus();
            else if (input == 11 ) reportOpportunityByStatusAndCity();
        }
    }

    public void reportLeadBySalesRep(){
        SalesRep salesRep = salesRepMenu.getSalesRepFromInputId();

        System.out.println(salesRep.getName()+"have "+ leadRepository.countBySalesRep(salesRep) + "lead");

        anythingToContinue();
    }

    private void reportOpportunityByStatusAndCity() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        System.out.println("Cities");
        for (Account account : accountList) {
            System.out.println("Account: "+ account );
            System.out.println(account.getCity());
            //Account.opportunityList.get(0).getId();
        }

        String city = inputAutowired.promptTextWithValidation("Choose City", List.of(Validation.STRING));
        printHeading(" \n Choose the status for opportunity " + " \n ");
        System.out.println("(1) Report OPEN by SalesRep");
        System.out.println("(2) Report CLOSED-WON by SalesRep");
        System.out.println("(3) Report CLOSED-LOST by SalesRep");

        Integer newInput = inputAutowired.promptIntWithValidation("-> ", 3);
        if (newInput == 1) {
            System.out.println(opportunityRepository.countByStatusAndAccount_City(OPEN, city));
        } else if (newInput == 2) {
            System.out.println(opportunityRepository.countByStatusAndAccount_City(CLOSED_WON, city));
        } else if (newInput == 3) {
            System.out.println(opportunityRepository.countByStatusAndAccount_City(CLOSED_LOST, city));
        }

        anythingToContinue();
    }

    private void reportOpportunityBySalesRepAndStatus() {
        clearConsole();
        SalesRep salesRep = salesRepMenu.getSalesRepFromInputId();
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

        anythingToContinue();
    }
}
