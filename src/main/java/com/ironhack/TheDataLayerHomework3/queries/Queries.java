package com.ironhack.TheDataLayerHomework3.queries;

import com.ironhack.TheDataLayerHomework3.enums.Industry;
import com.ironhack.TheDataLayerHomework3.enums.Product;
import com.ironhack.TheDataLayerHomework3.enums.Validation;
import com.ironhack.TheDataLayerHomework3.menu.SalesRepMenu;
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
public class Queries {

    final private Input inputAutowired;
    final private LeadRepository leadRepository;
    final private OpportunityRepository opportunityRepository;

    final private AccountRepository accountRepository;
    final private SalesRepMenu salesRepMenu;

    public Queries(Input inputAutowired, LeadRepository leadRepository, OpportunityRepository opportunityRepository, AccountRepository accountRepository, SalesRepMenu salesRepMenu) {
        this.inputAutowired = inputAutowired;
        this.leadRepository = leadRepository;
        this.opportunityRepository = opportunityRepository;
        this.accountRepository = accountRepository;
        this.salesRepMenu = salesRepMenu;
    }



    //SalesRep Queries

    public void reportLeadBySalesRep(){
        clearConsole();
        SalesRep salesRep = salesRepMenu.getSalesRepFromInputId();

        System.out.println(salesRep.getName()+" has "+ leadRepository.countBySalesRep(salesRep) + " Leads");

        anythingToContinue();
    }

    public void reportOpportunityBySalesRep() {
        clearConsole();
        SalesRep salesRep = salesRepMenu.getSalesRepFromInputId();

        System.out.println(salesRep.getName()+" has "+ opportunityRepository.countBySalesRep(salesRep) + " Opportunities");

        anythingToContinue();
    }

    public void reportOpportunityBySalesRepAndStatus() {
        clearConsole();
        SalesRep salesRep = salesRepMenu.getSalesRepFromInputId();
        printHeading(" \n Choose the Status to report " + " \n ");
        System.out.println("(1) Report OPEN by SalesRep");
        System.out.println("(2) Report CLOSED-WON by SalesRep");
        System.out.println("(3) Report CLOSED-LOST by SalesRep");


        Integer newInput = inputAutowired.promptIntWithValidation("-> ", 3);
        if (newInput == 1) {
            System.out.println( "Your count is: "
                    + opportunityRepository.countBySalesRepAndStatus(salesRep, OPEN));
        } else if (newInput == 2) {
            System.out.println( "Your count is: "
                    + opportunityRepository.countBySalesRepAndStatus(salesRep, CLOSED_WON));
        } else if (newInput == 3) {
            System.out.println( "Your count is: "
                    + opportunityRepository.countBySalesRepAndStatus(salesRep, CLOSED_LOST));
        }

        anythingToContinue();
    }


    //Product Queries


    public void reportOpportunityByStatusAndProduct() {
        clearConsole();

        printHeading(" \n Choose the Product type to report " + " \n ");
        System.out.println("(1) HYBRID");
        System.out.println("(2) FLATBED");
        System.out.println("(3) BOX");

        Product product = null;

        int input = inputAutowired.promptIntWithValidation("-> ", 3);
        if (input == 1) {
            product = Product.HYBRID;
        } else if (input == 2) {
            product = Product.FLATBED;
        } else if (input == 3) {
            product = Product.BOX;
        }

        printHeading(" \n Choose the Status to report " + " \n ");
        System.out.println("(1) OPEN");
        System.out.println("(2) CLOSED-WON");
        System.out.println("(3) CLOSED-LOST");

        Integer newInput = inputAutowired.promptIntWithValidation("-> ", 3);
        if (newInput == 1) {
            System.out.println( "Your count is: "
                    + opportunityRepository.countByProductAndStatus(product, OPEN));

        } else if (newInput == 2) {
            System.out.println( "Your count is: "
                    + opportunityRepository.countByProductAndStatus(product, CLOSED_WON));
        } else if (newInput == 3) {
            System.out.println( "Your count is: "
                    + opportunityRepository.countByProductAndStatus(product, CLOSED_LOST));
        }
        anythingToContinue();
    }

    public void reportOpportunityByProduct() {
        clearConsole();

        printHeading(" \n Choose the Product type to report " + " \n ");
        System.out.println("(1) HYBRID");
        System.out.println("(2) FLATBED");
        System.out.println("(3) BOX");

        Product product = null;

        int input = inputAutowired.promptIntWithValidation("-> ", 3);
        if (input == 1) {
            product = Product.HYBRID;
            System.out.println( "Your count is: "
                    + opportunityRepository.countByProduct(product));
        } else if (input == 2) {
            product = Product.FLATBED;
            System.out.println( "Your count is: "
                    + opportunityRepository.countByProduct(product));
        } else if (input == 3) {
            product = Product.BOX;
            System.out.println( "Your count is: "
                    + opportunityRepository.countByProduct(product));
        }
        anythingToContinue();
    }



    // Country Queries

    public void reportOpportunityByCountry() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        printHeading("Countries");
        for (Account account : accountList) {
            System.out.println("Account: "+ account.getAccountId() );
            System.out.println(account.getCountry());
        }

        String accountId = inputAutowired.promptTextWithValidation(
                "Insert the Account ID", List.of(Validation.ACCOUNT));

        Account foundAccount = accountRepository.findById(accountId).get();
        String country = foundAccount.getCountry();

        System.out.println("Your count is: " +
                opportunityRepository.countByAccount_Country(accountId, country));

        anythingToContinue();
    }

    public void reportOpportunityByStatusAndCountry() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        System.out.println("Countries");
        for (Account account : accountList) {
            System.out.println("Account: "+ account.getAccountId() );
            System.out.println(account.getCountry());
        }

        String accountId = inputAutowired.promptTextWithValidation(
                "Insert the Account ID", List.of(Validation.ACCOUNT));

        Account foundAccount = accountRepository.findById(accountId).get();
        String country = foundAccount.getCountry();

        printHeading(" \n Choose the Status to report " + " \n");
        System.out.println("(1) OPEN");
        System.out.println("(2) CLOSED-WON");
        System.out.println("(3) CLOSED-LOST");


        Integer newInput = inputAutowired.promptIntWithValidation("-> ", 3);
        if (newInput == 1) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_Country(accountId, country,  OPEN));
        } else if (newInput == 2) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_Country(accountId, country, CLOSED_WON));
        } else if (newInput == 3) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_Country(accountId, country, CLOSED_LOST));
        }

        anythingToContinue();

    }





    //City Queries

    public void reportOpportunityByCity() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        printHeading("Cities");
        for (Account account : accountList) {
            System.out.println("Account: "+ account.getAccountId() );
            System.out.println(account.getCity());
        }

        String accountId = inputAutowired.promptTextWithValidation(
                "Insert the Account ID", List.of(Validation.ACCOUNT));

        Account foundAccount = accountRepository.findById(accountId).get();
        String city = foundAccount.getCity();

        System.out.println("Your count is: " +
                opportunityRepository.countByAccount_City(accountId, city));

        anythingToContinue();
    }

    public void reportOpportunityByStatusAndCity() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        System.out.println("Cities");
        for (Account account : accountList) {
            System.out.println("Account: "+ account );
            System.out.println(account.getCity());
        }


        String accountId = inputAutowired.promptTextWithValidation(
                "Insert the Account ID", List.of(Validation.ACCOUNT));

        Account foundAccount = accountRepository.findById(accountId).get();
        String city = foundAccount.getCity();

        printHeading(" \n Choose the Status to report " + " \n");
        System.out.println("(1) OPEN");
        System.out.println("(2) CLOSED-WON");
        System.out.println("(3) CLOSED-LOST");


        Integer newInput = inputAutowired.promptIntWithValidation("-> ", 3);
        if (newInput == 1) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_City(accountId, city,  OPEN));
        } else if (newInput == 2) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_City(accountId, city, CLOSED_WON));
        } else if (newInput == 3) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_City(accountId, city, CLOSED_LOST));
        }

        anythingToContinue();
    }


    //Industry queries


    public void reportOpportunityByIndustry() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        printHeading("Industries");
        for (Account account : accountList) {
            System.out.println("Account: "+ account.getAccountId() );
            System.out.println(account.getIndustry());
        }

        String accountId = inputAutowired.promptTextWithValidation(
                "Insert the Account ID", List.of(Validation.ACCOUNT));

        Account foundAccount = accountRepository.findById(accountId).get();
        Industry industry = foundAccount.getIndustry();

        System.out.println("Your count is: " +
                opportunityRepository.countByAccount_Industry(accountId, industry));

        anythingToContinue();

    }

    public void reportOpportunityByStatusAndIndustry() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        System.out.println("Industries");
        for (Account account : accountList) {
            System.out.println("Account: "+ account );
            System.out.println(account.getIndustry());
        }


        String accountId = inputAutowired.promptTextWithValidation(
                "Insert the Account ID", List.of(Validation.ACCOUNT));

        Account foundAccount = accountRepository.findById(accountId).get();
        Industry industry = foundAccount.getIndustry();

        printHeading(" \n Choose the Status to report " + " \n");
        System.out.println("(1) OPEN");
        System.out.println("(2) CLOSED-WON");
        System.out.println("(3) CLOSED-LOST");


        Integer newInput = inputAutowired.promptIntWithValidation("-> ", 3);
        if (newInput == 1) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_Industry(accountId, industry,  OPEN));
        } else if (newInput == 2) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_Industry(accountId, industry, CLOSED_WON));
        } else if (newInput == 3) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_Industry(accountId, industry, CLOSED_LOST));
        }

        anythingToContinue();
    }







    public void minEmployeeCount() {
    }

    public void medianEmployeeCount() {
    }

    public void meanEmployeeCount() {
    }

    public void maxEmployeeCount() {
    }




    public void minQuantityProductCount() {
    }

    public void maxQuantityProductCount() {
    }

    public void medianQuantityProductCount() {
    }

    public void meanQuantityProductCount() {
    }



    public void minOpportunityByAccountCount() {
    }

    public void maxOpportunityByAccountCount() {
    }

    public void medianOpportunityByAccountCount() {
    }

    public void meanOpportunityByAccountCount() {
    }


}
