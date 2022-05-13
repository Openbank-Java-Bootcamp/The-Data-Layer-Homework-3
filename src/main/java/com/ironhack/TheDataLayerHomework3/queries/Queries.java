package com.ironhack.TheDataLayerHomework3.queries;

import com.ironhack.TheDataLayerHomework3.enums.Industry;
import com.ironhack.TheDataLayerHomework3.enums.Product;
import com.ironhack.TheDataLayerHomework3.enums.Validation;
import com.ironhack.TheDataLayerHomework3.menu.AccountMenu;
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

    final private AccountMenu accountMenu;

    public Queries(Input inputAutowired, LeadRepository leadRepository, OpportunityRepository opportunityRepository, AccountRepository accountRepository, SalesRepMenu salesRepMenu, AccountMenu accountMenu) {
        this.inputAutowired = inputAutowired;
        this.leadRepository = leadRepository;
        this.opportunityRepository = opportunityRepository;
        this.accountRepository = accountRepository;
        this.salesRepMenu = salesRepMenu;
        this.accountMenu = accountMenu;
    }


    //SalesRep Queries

    public void reportLeadBySalesRep() {
        clearConsole();
        SalesRep salesRep = salesRepMenu.getSalesRepFromInputId();

        System.out.println(salesRep.getName() + " has " + leadRepository.countBySalesRep(salesRep) + " Leads");

        anythingToContinue();
    }

    public void reportOpportunityBySalesRep() {
        clearConsole();
        SalesRep salesRep = salesRepMenu.getSalesRepFromInputId();

        System.out.println(salesRep.getName() + " has " + opportunityRepository.countBySalesRep(salesRep) + " Opportunities");

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
            System.out.println("Your count is: "
                    + opportunityRepository.countBySalesRepAndStatus(salesRep, OPEN));
        } else if (newInput == 2) {
            System.out.println("Your count is: "
                    + opportunityRepository.countBySalesRepAndStatus(salesRep, CLOSED_WON));
        } else if (newInput == 3) {
            System.out.println("Your count is: "
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
            System.out.println("Your count is: "
                    + opportunityRepository.countByProductAndStatus(product, OPEN));

        } else if (newInput == 2) {
            System.out.println("Your count is: "
                    + opportunityRepository.countByProductAndStatus(product, CLOSED_WON));
        } else if (newInput == 3) {
            System.out.println("Your count is: "
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
            System.out.println("Your count is: "
                    + opportunityRepository.countByProduct(product));
        } else if (input == 2) {
            product = Product.FLATBED;
            System.out.println("Your count is: "
                    + opportunityRepository.countByProduct(product));
        } else if (input == 3) {
            product = Product.BOX;
            System.out.println("Your count is: "
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
            System.out.println("Account: " + account.getAccountId());
            System.out.println(account.getCountry());
        }

        String country = inputAutowired.promptTextWithValidation(
                "\nInsert the Country", List.of(Validation.COUNTRY));

        System.out.println("Your count is: " +
                opportunityRepository.countByAccount_Country(country));

        anythingToContinue();
    }

    public void reportOpportunityByStatusAndCountry() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        printHeading("Countries");
        for (Account account : accountList) {
            System.out.println(account.getCountry());
        }

        String country = inputAutowired.promptTextWithValidation(
                "\nInsert the Country", List.of(Validation.COUNTRY));

        printHeading(" \n Choose the Status to report " + " \n");
        System.out.println("(1) OPEN");
        System.out.println("(2) CLOSED-WON");
        System.out.println("(3) CLOSED-LOST");


        Integer newInput = inputAutowired.promptIntWithValidation("-> ", 3);
        if (newInput == 1) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_Country(OPEN, country));
        } else if (newInput == 2) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_Country(CLOSED_WON, country));
        } else if (newInput == 3) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_Country(CLOSED_LOST, country));
        }

        anythingToContinue();

    }

    //City Queries

    public void reportOpportunityByCity() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        printHeading("Cities");
        for (Account account : accountList) {
            System.out.println(account.getCity());
        }

        String city = inputAutowired.promptTextWithValidation(
                "\nInsert the City", List.of(Validation.STRING));

        System.out.println("Your count is: " +
                opportunityRepository.countByAccount_City(city));

        anythingToContinue();
    }

    public void reportOpportunityByStatusAndCity() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        printHeading("Cities");
        for (Account account : accountList) {
            System.out.println(account.getCity());
        }

        String city = inputAutowired.promptTextWithValidation(
                "\nInsert the City", List.of(Validation.STRING));

        printHeading(" \n Choose the Status to report " + " \n");
        System.out.println("(1) OPEN");
        System.out.println("(2) CLOSED-WON");
        System.out.println("(3) CLOSED-LOST");


        Integer newInput = inputAutowired.promptIntWithValidation("-> ", 3);
        if (newInput == 1) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_City(OPEN, city));
        } else if (newInput == 2) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_City(CLOSED_WON, city));
        } else if (newInput == 3) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_City(CLOSED_LOST, city));
        }

        anythingToContinue();
    }

    //Industry queries

    public void reportOpportunityByIndustry() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        printHeading("Industries");

        Industry industry = accountMenu.selectIndustry();

        System.out.println("Your count is: " +
                opportunityRepository.countByAccount_Industry(industry));

        anythingToContinue();

    }

    public void reportOpportunityByStatusAndIndustry() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        printHeading("Industries");

        Industry industry = accountMenu.selectIndustry();

        printHeading(" \n Choose the Status to report " + " \n");
        System.out.println("(1) OPEN");
        System.out.println("(2) CLOSED-WON");
        System.out.println("(3) CLOSED-LOST");


        Integer newInput = inputAutowired.promptIntWithValidation("-> ", 3);
        if (newInput == 1) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_Industry(OPEN, industry));
        } else if (newInput == 2) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_Industry(CLOSED_WON, industry));
        } else if (newInput == 3) {
            System.out.println("Your count is: " +
                    opportunityRepository.countByStatusAndAccount_Industry(CLOSED_LOST, industry));
        }

        anythingToContinue();
    }

    //EmployeeCount States

    public void minEmployeeCount() {
        clearConsole();
        System.out.println("The minimum Employee count is: " + accountRepository.minEmployee());

        anythingToContinue();
    }

    public void maxEmployeeCount() {
        clearConsole();
        System.out.println("The maximum Employee count is: " + accountRepository.maxEmployee());
        anythingToContinue();
    }

    public void medianEmployeeCount() {
        clearConsole();
        System.out.println("The median Employee count is: " + accountRepository.medianEmployee());
        anythingToContinue();
    }

    public void meanEmployeeCount() {
        clearConsole();
        System.out.println("The mean Employee count is: " + accountRepository.avgEmployee());
        anythingToContinue();
    }

    //Quantity States

    public void minQuantityProductCount() {
        clearConsole();
        System.out.println("The minimum product quantity is: " + opportunityRepository.minQuantity());
        anythingToContinue();
    }

    public void maxQuantityProductCount() {
        clearConsole();
        System.out.println("The maximum product quantity is: " + opportunityRepository.maxQuantity());
        anythingToContinue();
    }

    public void medianQuantityProductCount() {
        clearConsole();
        System.out.println("The median product quantity is: " + opportunityRepository.medianQuantity());
        anythingToContinue();
    }

    public void meanQuantityProductCount() {
        clearConsole();
        System.out.println("The mean product quantity is: " + opportunityRepository.avgQuantity());
        anythingToContinue();
    }

    //Opportunity States

    public void minOpportunityByAccountCount() {
        clearConsole();
        System.out.println("The minimum opportunity count is: " + opportunityRepository.minOpportunities());
        anythingToContinue();
    }

    public void maxOpportunityByAccountCount() {
        clearConsole();
        System.out.println("The maximum opportunity count is: " + opportunityRepository.maxOpportunities());
        anythingToContinue();
    }

    public void medianOpportunityByAccountCount() {
        clearConsole();
        anythingToContinue();
    }

    public void meanOpportunityByAccountCount() {
        clearConsole();
        anythingToContinue();
    }

}
