package com.ironhack.TheDataLayerHomework3.menu;


import com.ironhack.TheDataLayerHomework3.enums.Validation;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import com.ironhack.TheDataLayerHomework3.utils.Utils;
import com.ironhack.TheDataLayerHomework3.repository.SalesRepRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ironhack.TheDataLayerHomework3.utils.Input.promptIntWithValidation;
import static com.ironhack.TheDataLayerHomework3.utils.Input.promptTextWithValidation;
import static com.ironhack.TheDataLayerHomework3.utils.Utils.clearConsole;
import static com.ironhack.TheDataLayerHomework3.utils.Utils.printHeading;

@Component
public class SalesRepMenu {


    //same as @Autowired SalesRepRepository
    final private SalesRepRepository salesRepRepository;

    public SalesRepMenu(SalesRepRepository salesRepRepository) {
        this.salesRepRepository = salesRepRepository;
    }


    public void menu() {
        int input = 0;

        while (input != 99) {

            input = promptIntWithValidation("(1) Create Sales Rep \n (2) Show all Sales Rep", 99);

            if (input == 1) createNewSalesRep();
            else if (input == 2) showAllSalesRep();

        }

        //TODO if 99 back
        System.exit(0);
    }

    public void createNewSalesRep() {

        clearConsole();
        printHeading("Please input the following Sales Rep information");

        String newSalesRepName = promptTextWithValidation("Insert the SalesRep name", List.of(Validation.NAME));

        SalesRep currentSalesRep = new SalesRep(newSalesRepName);

        SalesRep storedSalesRep = salesRepRepository.save(currentSalesRep);

        System.out.println("Successfully created Sales Rep");
        System.out.println(storedSalesRep);

        Utils.anythingToContinue();
        Utils.clearConsole();
    }

    public void showAllSalesRep() {

        clearConsole();

        var salesRepList = salesRepRepository.findAll();

        for (SalesRep salesRep : salesRepList) {
            System.out.println(salesRep);
        }

        Utils.anythingToContinue();
        Utils.clearConsole();
    }
}
