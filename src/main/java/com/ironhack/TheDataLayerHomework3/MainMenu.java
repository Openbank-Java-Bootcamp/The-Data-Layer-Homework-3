package com.ironhack.TheDataLayerHomework3;

import com.ironhack.TheDataLayerHomework3.enums.Validation;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import com.ironhack.TheDataLayerHomework3.navigation.Navigation;
import com.ironhack.TheDataLayerHomework3.repository.SalesRepRepository;
import com.ironhack.TheDataLayerHomework3.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ironhack.TheDataLayerHomework3.utils.Input.promptIntWithValidation;
import static com.ironhack.TheDataLayerHomework3.utils.Input.promptTextWithValidation;
import static com.ironhack.TheDataLayerHomework3.utils.Utils.clearConsole;
import static com.ironhack.TheDataLayerHomework3.utils.Utils.printHeading;

@Component
public class MainMenu {

    final private SalesRepRepository salesRepRepository;

    //Dependency Injection AKA autowiring in the constructor
    public MainMenu(SalesRepRepository salesRepRepository) {
        this.salesRepRepository = salesRepRepository;
    }

    public void menu() {
        int input = 0;

        while (input != 99) {

            input = promptIntWithValidation("(1) Create Sales Rep \n (2) Show all Sales Rep", 99);

            if (input == 1) createNewSalesRep();
            else if (input == 2) showAllSalesRep();

        }

        System.exit(0);
    }

    public void createNewSalesRep() {

        clearConsole();
        printHeading("Please input the following Sales Rep information");

        String newSalesRepName = promptTextWithValidation("Insert the SalesRep name", List.of(Validation.NAME));

        var currentSalesRep = new SalesRep(newSalesRepName);

        salesRepRepository.save(currentSalesRep);

        System.out.println("Successfully created Sales Rep");

    }

    public void showAllSalesRep(){

        clearConsole();

        var salesRepList = salesRepRepository.findAll();

        for (SalesRep salesRep : salesRepList){
            System.out.println(salesRep);
        }

    }
}
