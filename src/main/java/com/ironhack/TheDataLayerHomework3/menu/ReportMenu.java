package com.ironhack.TheDataLayerHomework3.menu;

import com.ironhack.TheDataLayerHomework3.enums.Validation;
import com.ironhack.TheDataLayerHomework3.models.Account;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import com.ironhack.TheDataLayerHomework3.queries.Queries;
import com.ironhack.TheDataLayerHomework3.repository.AccountRepository;
import com.ironhack.TheDataLayerHomework3.repository.LeadRepository;
import com.ironhack.TheDataLayerHomework3.repository.OpportunityRepository;
import com.ironhack.TheDataLayerHomework3.utils.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ironhack.TheDataLayerHomework3.enums.Status.*;
import static com.ironhack.TheDataLayerHomework3.utils.Utils.*;

@Component
public class ReportMenu {

    @Autowired
    private Input inputAutowired;

    @Autowired
    private Queries queries;

    public void menu() {
        int input = 0;

        while (input != 99) {
            clearConsole();
            input = inputAutowired.promptIntWithValidation(
                     "\n(1) By SalesRep" +
                            "\n(2) By Product" +
                            "\n(3) By Country" +
                            "\n(4) By City" +
                            "\n(5) By Industry" +
                            "\n(6) Employee Count States" +
                            "\n(7) Quantity States" +
                             "\n(8) Opportunity States" +
                            "\n\n(99) Go Back", 99);


            if (input == 1 ) bySalesRepMenu();
            else if (input == 2 ) byProductMenu();
            else if (input == 3 ) byCountryMenu();
            else if (input == 4 ) byCityMenu();
            else if (input == 5 ) byIndustryMenu();
            else if (input == 6 ) employeeCountStates();
            else if (input == 7 ) productQuantityStates();
            else if (input == 8 ) opportunityByAccountStates();
        }
    }

    public void bySalesRepMenu() {
        int input = 0;

        while (input != 99) {
            clearConsole();
            input = inputAutowired.promptIntWithValidation(
                    "\n(1) Report Lead by SalesRep" +
                    "\n(2) Report Opportunity by SalesRep" +
                    "\n(3) Report Opportunity Status by SalesRep" +
                    "\n\n(99) Go Back", 99);


            if (input == 1 ) queries.reportLeadBySalesRep();
            else if (input == 2 ) queries.reportOpportunityBySalesRep();
            else if (input == 3 ) queries.reportOpportunityBySalesRepAndStatus();
        }
    }


    public void byProductMenu() {
        int input = 0;

        while (input != 99) {
            clearConsole();
            input = inputAutowired.promptIntWithValidation(
                    //TODO TODO
                            "\n(1) Report Opportunity by Product" +
                            "\n(2) Report Opportunity Status by Product" +
                            "\n\n(99) Go Back", 99);

            if (input == 1 ) queries.reportOpportunityByProduct();
            else if (input == 2 ) queries.reportOpportunityByStatusAndProduct();
        }
    }


    public void byCityMenu() {
        int input = 0;

        while (input != 99) {
            clearConsole();
            input = inputAutowired.promptIntWithValidation(
                    //TODO TODO
                            "\n(1) Report Opportunity by City" +
                            "\n(2) Report Opportunity Status by City" +
                            "\n\n(99) Go Back", 99);

            if (input == 1 ) queries.reportOpportunityByCity();
            else if (input == 2 ) queries.reportOpportunityByStatusAndCity();
        }
    }


    public void byCountryMenu() {
        int input = 0;

        while (input != 99) {
            clearConsole();
            input = inputAutowired.promptIntWithValidation(
                    //TODO TODO
                            "\n(1) Report Opportunity by Country" +
                            "\n(2) Report Opportunity Status by Country" +
                            "\n\n(99) Go Back", 99);

            if (input == 1 ) queries.reportOpportunityByCountry();
            else if (input == 2 ) queries.reportOpportunityByStatusAndCountry();
        }
    }

    public void byIndustryMenu() {
        int input = 0;

        while (input != 99) {
            clearConsole();
            input = inputAutowired.promptIntWithValidation(
                    //TODO TODO
                            "\n(1) Report Opportunity by Industry" +
                            "\n(2) Report Opportunity Status by Industry" +
                            "\n\n(99) Go Back", 99);

            if (input == 1 ) queries.reportOpportunityByIndustry();
            else if (input == 2 ) queries.reportOpportunityByStatusAndIndustry();
        }
    }

    public void employeeCountStates() {
        int input = 0;

        while (input != 99) {
            clearConsole();
            input = inputAutowired.promptIntWithValidation(
                    //TODO TODO
                            "\n(1)  Mean Employee Count" +
                            "\n(2)  Median Employee Count" +
                            "\n(3)  Max Employee Count" +
                            "\n(4)  Min Employee Count" +
                             "\n\n(99) Go Back", 99);

            if (input == 1 ) queries.meanEmployeeCount();
            else if (input == 2 ) queries.medianEmployeeCount();
            else if (input == 3 ) queries.maxEmployeeCount();
            else if (input == 4 ) queries.minEmployeeCount();
        }
    }

    public void productQuantityStates() {
        int input = 0;

        while (input != 99) {
            clearConsole();
            input = inputAutowired.promptIntWithValidation(
                    //TODO TODO
                    "\n(1)  Mean Quantity Product Count" +
                            "\n(2)  Median Quantity Product Count" +
                            "\n(3)  Max Quantity Product Count" +
                            "\n(4)  Min Quantity Product Count" +
                            "\n\n(99) Go Back", 99);

            if (input == 1 ) queries.meanQuantityProductCount();
            else if (input == 2 ) queries.medianQuantityProductCount();
            else if (input == 3 ) queries.maxQuantityProductCount();
            else if (input == 4 ) queries.minQuantityProductCount();
        }
    }

    public void opportunityByAccountStates() {
        int input = 0;

        while (input != 99) {
            clearConsole();
            input = inputAutowired.promptIntWithValidation(
                    //TODO TODO
                    "\n(1)  Mean Opportunity by Account Count" +
                            "\n(2)  Median Opportunity by Account Count" +
                            "\n(3)  Max Opportunity by Account Count" +
                            "\n(4)  Min Opportunity by Account Count" +
                            "\n\n(99) Go Back", 99);

            if (input == 1 ) queries.meanOpportunityByAccountCount();
            else if (input == 2 ) queries.medianOpportunityByAccountCount();
            else if (input == 3 ) queries.maxOpportunityByAccountCount();
            else if (input == 4 ) queries.minOpportunityByAccountCount();
        }
    }

}
