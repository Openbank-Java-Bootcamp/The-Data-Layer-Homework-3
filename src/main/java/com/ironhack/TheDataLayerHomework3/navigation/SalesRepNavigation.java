package com.ironhack.TheDataLayerHomework3.navigation;
import com.ironhack.TheDataLayerHomework3.enums.Validation;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import com.ironhack.TheDataLayerHomework3.utils.Utils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.util.ArrayList;
import java.util.List;

import static com.ironhack.TheDataLayerHomework3.utils.Input.promptTextWithValidation;
import static com.ironhack.TheDataLayerHomework3.utils.Utils.clearConsole;
import static com.ironhack.TheDataLayerHomework3.utils.Utils.printHeading;

@EnableJpaRepositories(basePackages = "com.ironhack.TheDataLayerHomework3.repository")
public class SalesRepNavigation {

    static SalesRep currentSalesRep;

    public static List<SalesRep> salesRepList = new ArrayList<>();



    public static void createNewSalesRep() {

        clearConsole();
        printHeading("Please input the following Sales Rep information");

        String newSalesRepName = promptTextWithValidation("Insert the SalesRep name", List.of(Validation.NAME));

        currentSalesRep = new SalesRep(newSalesRepName);

        salesRepList.add(currentSalesRep);
       // SalesRepService.test(currentSalesRep);

        System.out.println(salesRepList.get(salesRepList.size() - 1).toString());

        Utils.anythingToContinue();
        Utils.clearConsole();
        Navigation.startNavigation();
    }

    public static void showSalesReps() {
        if (!salesRepList.isEmpty()) {
            Utils.printHeading("- Your current SalesReps - ");

            for (SalesRep salesRep : salesRepList) {
               System.out.println(salesRep);
            }
        } else {
            Utils.printLikeError("No Sales Reps in the database, please create one");
        }
        Utils.anythingToContinue();
        Utils.clearConsole();
        Navigation.startNavigation();
    }
}
