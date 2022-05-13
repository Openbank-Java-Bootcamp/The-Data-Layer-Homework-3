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
import com.ironhack.TheDataLayerHomework3.utils.Colors;
import com.ironhack.TheDataLayerHomework3.utils.Input;
import org.springframework.stereotype.Component;


import java.awt.*;
import java.util.ArrayList;
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

        printHeading(salesRep.getName() + " has " + leadRepository.countBySalesRep(salesRep) + " Leads");

        anythingToContinue();
    }

    public void reportOpportunityBySalesRep() {
        clearConsole();
        SalesRep salesRep = salesRepMenu.getSalesRepFromInputId();

        printHeading(salesRep.getName() + " has " + opportunityRepository.countBySalesRep(salesRep) + " Opportunities");

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
            printHeading(salesRep.getName() +  " has " + opportunityRepository.countBySalesRepAndStatus(salesRep, OPEN)+ " OPEN Opportunites");
        } else if (newInput == 2) {
            printHeading(salesRep.getName() +  " has "+ opportunityRepository.countBySalesRepAndStatus(salesRep, CLOSED_WON) + " CLOSED_WON Opportunites");
        } else if (newInput == 3) {
            printHeading(salesRep.getName() +  " has "+ opportunityRepository.countBySalesRepAndStatus(salesRep, CLOSED_LOST) + " CLOSED_LOST Opportunites");
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
            printHeading(opportunityRepository.countByProductAndStatus(product, OPEN)+
                    " opportunities have an OPEN status and a "+ product + " product type ");

        } else if (newInput == 2) {
            printHeading(opportunityRepository.countByProductAndStatus(product, CLOSED_WON)+
                    " opportunities have a CLOSED_WON status and a "+ product + " product type ");
        } else if (newInput == 3) {
            printHeading( opportunityRepository.countByProductAndStatus(product, CLOSED_LOST)+
                    " opportunities have a CLOSED_LOST status and a "+ product + " product type ");
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
            printHeading(opportunityRepository.countByProduct(product)+
                    " opportunities have a "+ product + " product type ");
        } else if (input == 2) {
            product = Product.FLATBED;
            printHeading(opportunityRepository.countByProduct(product)+
                    " opportunities have a "+ product + " product type ");
        } else if (input == 3) {
            product = Product.BOX;
            printHeading(opportunityRepository.countByProduct(product)+
                    " opportunities have a "+ product + " product type ");
        }
        anythingToContinue();
    }

    // Country Queries

    public void reportOpportunityByCountry() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        printHeading("Countries");
        List<String> Accountscountries = new ArrayList<>();
        for (Account account : accountList) {
            if(!Accountscountries.contains(account.getCountry().toLowerCase()))
                Accountscountries.add(account.getCountry().toLowerCase());
        }
        System.out.println(Accountscountries);

        String country = inputAutowired.promptTextWithValidation(
                "\n\nInsert the Country", List.of(Validation.COUNTRY));

        printHeading(opportunityRepository.countByAccount_Country(country)+
                " opportunities in "+ country.toUpperCase());

        anythingToContinue();
    }


    public void reportOpportunityByStatusAndCountry() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        printHeading("Countries");
        List<String> Accountscountries = new ArrayList<>();
        for (Account account : accountList) {
            if (!Accountscountries.contains(account.getCountry().toLowerCase()))
                Accountscountries.add(account.getCountry().toLowerCase());
        }
        System.out.println(Accountscountries);

        String country = inputAutowired.promptTextWithValidation(
                "\n\nInsert the Country", List.of(Validation.COUNTRY));

        printHeading(" \n Choose the Status to report " + " \n");
        System.out.println("(1) OPEN");
        System.out.println("(2) CLOSED-WON");
        System.out.println("(3) CLOSED-LOST");


        Integer newInput = inputAutowired.promptIntWithValidation("-> ", 3);
        if (newInput == 1) {
            printHeading(opportunityRepository.countByStatusAndAccount_Country(OPEN, country) +
                    " OPEN opportunities in " + country.toUpperCase());
        } else if (newInput == 2) {
            printHeading(opportunityRepository.countByStatusAndAccount_Country(CLOSED_WON, country) +
                    " CLOSED_WON opportunities in " + country.toUpperCase());
        } else if (newInput == 3) {
            printHeading(opportunityRepository.countByStatusAndAccount_Country(CLOSED_LOST, country) +
                    " CLOSED_LOST opportunities in " + country.toUpperCase());
        }
        anythingToContinue();
    }

    //City Queries

    public void reportOpportunityByCity() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        printHeading("Cities");
        List<String> Accountscities = new ArrayList<>();
        for (Account account : accountList) {
            if (!Accountscities.contains(account.getCity().toLowerCase()))
                Accountscities.add(account.getCity().toLowerCase());
        }
        System.out.println(Accountscities);

        String city = inputAutowired.promptTextWithValidation(
                "\n\nInsert the City", List.of(Validation.STRING));

        printHeading(opportunityRepository.countByAccount_City(city) + " opportunities in "+ city.toUpperCase());

        anythingToContinue();
    }

    public void reportOpportunityByStatusAndCity() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        printHeading("Cities");
        List<String> Accountscities = new ArrayList<>();
        for (Account account : accountList) {
            if (!Accountscities.contains(account.getCity().toLowerCase()))
                Accountscities.add(account.getCity().toLowerCase());
        }
        System.out.println(Accountscities);

        String city = inputAutowired.promptTextWithValidation(
                "\n\nInsert the City", List.of(Validation.STRING));

        printHeading(" \n Choose the Status to report " + " \n");
        System.out.println("(1) OPEN");
        System.out.println("(2) CLOSED-WON");
        System.out.println("(3) CLOSED-LOST");


        Integer newInput = inputAutowired.promptIntWithValidation("-> ", 3);
        if (newInput == 1) {
            printHeading(opportunityRepository.countByStatusAndAccount_City(OPEN, city) +
                    " OPEN opportunities in " + city.toUpperCase());
        } else if (newInput == 2) {
            printHeading(opportunityRepository.countByStatusAndAccount_City(CLOSED_WON, city)+
                    " CLOSED_WON opportunities in " + city.toUpperCase());
        } else if (newInput == 3) {
            printHeading(opportunityRepository.countByStatusAndAccount_City(CLOSED_LOST, city)+
                    " CLOSED_LOST opportunities in " + city.toUpperCase());
        }

        anythingToContinue();
    }

    //Industry queries

    public void reportOpportunityByIndustry() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();
        printHeading("Industries");

        Industry industry = accountMenu.selectIndustry();

        printHeading(opportunityRepository.countByAccount_Industry(industry)+
                " opportunities with " + industry.toString().toUpperCase() +" industry ");

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
            printHeading(opportunityRepository.countByStatusAndAccount_Industry(OPEN, industry) +
                    " OPEN opportunities with " + industry.toString().toUpperCase() +" industry ");
        } else if (newInput == 2) {
            printHeading(opportunityRepository.countByStatusAndAccount_Industry(CLOSED_WON, industry)+
                    " CLOSED_WON opportunities with " + industry.toString().toUpperCase() +" industry ");
        } else if (newInput == 3) {
            printHeading(opportunityRepository.countByStatusAndAccount_Industry(CLOSED_LOST, industry)+
                    " CLOSED_LOST opportunities with " + industry.toString().toUpperCase() +" industry ");
        }

        anythingToContinue();
    }

    //EmployeeCount States

    public void minEmployeeCount() {
        clearConsole();
        printHeading("The minimum Employee count is: " + accountRepository.minEmployee());

        anythingToContinue();
    }

    public void maxEmployeeCount() {
        clearConsole();
        printHeading("The maximum Employee count is: " + accountRepository.maxEmployee());
        anythingToContinue();
    }

    public void medianEmployeeCount() {
        clearConsole();

        List<Integer> employeeList = accountRepository.medianEmployee();

        double median;

        if (employeeList.size() % 2 == 0)
            median = ( employeeList.get(employeeList.size() / 2) +  employeeList.get(employeeList.size() / 2 - 1))/2;
        else
            median =  employeeList.get(employeeList.size() / 2);

        printHeading("The median Employee count is: " + median);
        anythingToContinue();
    }

    public void meanEmployeeCount() {
        clearConsole();
        printHeading("The mean Employee count is: " + accountRepository.avgEmployee());
        anythingToContinue();
    }

    //Quantity States


    public void minQuantityProductCount() {
        clearConsole();
        printHeading("The minimum product quantity is: " + opportunityRepository.minQuantity());
        anythingToContinue();
    }

    public void maxQuantityProductCount() {
        clearConsole();
        printHeading("The maximum product quantity is: " + opportunityRepository.maxQuantity());
        anythingToContinue();
    }

    public void medianQuantityProductCount() {
        clearConsole();

        List<Integer> quantityList = opportunityRepository.medianQuantity();

        double median;

        if (quantityList.size() % 2 == 0)
            median = ( quantityList.get(quantityList.size() / 2) + quantityList.get(quantityList.size() / 2 - 1))/2;
        else
            median = quantityList.get(quantityList.size() / 2);

        printHeading("The median product quantity is: " + median);
        anythingToContinue();
    }

    public void meanQuantityProductCount() {
        clearConsole();
        printHeading("The mean product quantity is: " + opportunityRepository.avgQuantity());
        anythingToContinue();
    }

    //Opportunity States

    public void minOpportunityByAccountCount() {
        clearConsole();
        printHeading("The minimum opportunity count is: " + opportunityRepository.minOpportunities());
        anythingToContinue();
    }

    public void maxOpportunityByAccountCount() {
        clearConsole();
        printHeading("The maximum opportunity count is: " + opportunityRepository.maxOpportunities());
        anythingToContinue();
    }

    public void medianOpportunityByAccountCount() {
        clearConsole();

        List<Integer> opportunitiesByAccountList = opportunityRepository.medianOpportunitiesByAccount();

        double median;

        if (opportunitiesByAccountList.size() % 2 == 0)
            median = ( opportunitiesByAccountList.get(opportunitiesByAccountList.size() / 2) + opportunitiesByAccountList.get(opportunitiesByAccountList.size() / 2 - 1))/2;
        else
            median = opportunitiesByAccountList.get(opportunitiesByAccountList.size() / 2);

        printHeading("The median opportunity by account is: " + median);
        anythingToContinue();
    }

    public void meanOpportunityByAccountCount() {
        clearConsole();

        List<Integer> opportunitiesByAccountList = opportunityRepository.avgOpportunitiesByAccount();

        int sum = 0;

        for (Integer d : opportunitiesByAccountList) sum += d;

        double avg = sum/opportunitiesByAccountList.size();

        printHeading("The mean opportunity by account is: " + avg);

        anythingToContinue();
    }

}
