package com.ironhack.TheDataLayerHomework3.menu;

import com.ironhack.TheDataLayerHomework3.enums.Validation;
import com.ironhack.TheDataLayerHomework3.models.Account;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import com.ironhack.TheDataLayerHomework3.queries.Queries;
import com.ironhack.TheDataLayerHomework3.repository.AccountRepository;
import com.ironhack.TheDataLayerHomework3.repository.LeadRepository;
import com.ironhack.TheDataLayerHomework3.repository.OpportunityRepository;
import com.ironhack.TheDataLayerHomework3.utils.Colors;
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
                     "\n(1)"+ Colors.GREEN_BOLD_BRIGHT +" By SalesRep" + Colors.RESET +
                            "\n(2)"+ Colors.BLUE_BOLD_BRIGHT +" By Product" + Colors.RESET +
                            "\n(3)"+ Colors.PURPLE_BOLD_BRIGHT +" By Country" + Colors.RESET +
                            "\n(4)"+ Colors.CYAN_BOLD_BRIGHT +" By City" + Colors.RESET +
                            "\n(5)"+ Colors.GREEN_BOLD_BRIGHT +" By Industry" + Colors.RESET +
                            "\n(6)"+ Colors.YELLOW_BOLD_BRIGHT +" Employee Count States" + Colors.RESET +
                            "\n(7)"+ Colors.CYAN_BOLD_BRIGHT +" Quantity States" + Colors.RESET +
                             "\n(8)"+ Colors.PURPLE_BOLD_BRIGHT +" Opportunity States" + Colors.RESET +
                            "\n\n(99)"+ Colors.RED +" Go Back" + Colors.RESET , 99);


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
                    "\n(1)"+ Colors.GREEN +" Report Lead by SalesRep"  + Colors.RESET +
                    "\n(2)"+ Colors.GREEN +" Report Opportunity by SalesRep"  + Colors.RESET +
                    "\n(3)"+ Colors.GREEN +" Report Opportunity Status by SalesRep"  + Colors.RESET +
                    "\n\n(99)"+ Colors.RED +" Go Back" + Colors.RESET, 99);


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
                            "\n(1)"+ Colors.BLUE +" Report Opportunity by Product" + Colors.RESET +
                            "\n(2)"+ Colors.BLUE +" Report Opportunity Status by Product" + Colors.RESET +
                            "\n\n(99)"+ Colors.RED +" Go Back"+ Colors.RESET , 99);

            if (input == 1 ) queries.reportOpportunityByProduct();
            else if (input == 2 ) queries.reportOpportunityByStatusAndProduct();
        }
    }

    public void byCountryMenu() {
        int input = 0;

        while (input != 99) {
            clearConsole();
            input = inputAutowired.promptIntWithValidation(
                    "\n(1)"+ Colors.PURPLE +" Report Opportunity by Country"+ Colors.RESET  +
                            "\n(2)"+ Colors.PURPLE +" Report Opportunity Status by Country"+ Colors.RESET  +
                            "\n\n(99)"+ Colors.RED +" Go Back"+ Colors.RESET, 99);

            if (input == 1 ) queries.reportOpportunityByCountry();
            else if (input == 2 ) queries.reportOpportunityByStatusAndCountry();
        }
    }

    public void byCityMenu() {
        int input = 0;

        while (input != 99) {
            clearConsole();
            input = inputAutowired.promptIntWithValidation(
                            "\n(1)" + Colors.CYAN +" Report Opportunity by City" + Colors.RESET  +
                            "\n(2)" + Colors.CYAN + " Report Opportunity Status by City" + Colors.RESET  +
                            "\n\n(99)"+ Colors.RED +" Go Back"+ Colors.RESET  , 99);

            if (input == 1 ) queries.reportOpportunityByCity();
            else if (input == 2 ) queries.reportOpportunityByStatusAndCity();
        }
    }


    public void byIndustryMenu() {
        int input = 0;

        while (input != 99) {
            clearConsole();
            input = inputAutowired.promptIntWithValidation(
                            "\n(1)" + Colors.GREEN +" Report Opportunity by Industry"+ Colors.RESET   +
                            "\n(2)" + Colors.GREEN + "  Report Opportunity Status by Industry"+ Colors.RESET   +
                            "\n\n(99)" + Colors.RED +" Go Back"+ Colors.RESET  , 99);

            if (input == 1 ) queries.reportOpportunityByIndustry();
            else if (input == 2 ) queries.reportOpportunityByStatusAndIndustry();
        }
    }

    public void employeeCountStates() {
        int input = 0;

        while (input != 99) {
            clearConsole();
            input = inputAutowired.promptIntWithValidation(
                            "\n(1)" + Colors.YELLOW +" Mean Employee Count"+ Colors.RESET    +
                            "\n(2)" + Colors.YELLOW +" Median Employee Count"+ Colors.RESET    +
                            "\n(3)" + Colors.YELLOW +" Max Employee Count"+ Colors.RESET    +
                            "\n(4)" + Colors.YELLOW +" Min Employee Count"+ Colors.RESET    +
                             "\n\n(99)" + Colors.RED +" Go Back"+ Colors.RESET   + Colors.RESET, 99);

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
                    "\n(1)" + Colors.CYAN +" Mean Quantity Product Count"+ Colors.RESET +
                            "\n(2)" + Colors.CYAN +" Median Quantity Product Count"+ Colors.RESET +
                            "\n(3)" + Colors.CYAN +" Max Quantity Product Count"+ Colors.RESET +
                            "\n(4)" + Colors.CYAN +" Min Quantity Product Count"+ Colors.RESET +
                            "\n\n(99)" + Colors.RED +" Go Back"+ Colors.RESET, 99);

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
                    "\n(1)" + Colors.PURPLE + " Mean Opportunity by Account Count" + Colors.RESET +
                            "\n(2)" + Colors.PURPLE + " Median Opportunity by Account Count" + Colors.RESET +
                            "\n(3)" + Colors.PURPLE + " Max Opportunity by Account Count" + Colors.RESET +
                            "\n(4)" + Colors.PURPLE + " Min Opportunity by Account Count" + Colors.RESET +
                            "\n\n(99)" + Colors.RED + " Go Back"+ Colors.RESET , 99);

            if (input == 1 ) queries.meanOpportunityByAccountCount();
            else if (input == 2 ) queries.medianOpportunityByAccountCount();
            else if (input == 3 ) queries.maxOpportunityByAccountCount();
            else if (input == 4 ) queries.minOpportunityByAccountCount();
        }
    }

}
