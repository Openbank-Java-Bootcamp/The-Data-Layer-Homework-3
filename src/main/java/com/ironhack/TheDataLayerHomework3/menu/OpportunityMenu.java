package com.ironhack.TheDataLayerHomework3.menu;


import com.ironhack.TheDataLayerHomework3.enums.Product;
import com.ironhack.TheDataLayerHomework3.enums.Status;
import com.ironhack.TheDataLayerHomework3.models.Contact;
import com.ironhack.TheDataLayerHomework3.models.Opportunity;
import com.ironhack.TheDataLayerHomework3.repository.OpportunityRepository;
import com.ironhack.TheDataLayerHomework3.utils.Input;
import org.springframework.stereotype.Component;

import static com.ironhack.TheDataLayerHomework3.enums.Status.OPEN;
import static com.ironhack.TheDataLayerHomework3.utils.Utils.clearConsole;
import static com.ironhack.TheDataLayerHomework3.utils.Utils.printHeading;

@Component
public class OpportunityMenu {
    final private Input inputAutowired;
    final private OpportunityRepository opportunityRepository;

    public OpportunityMenu(Input inputAutowired, OpportunityRepository opportunityRepository) {
        this.inputAutowired = inputAutowired;
        this.opportunityRepository = opportunityRepository;
    }

    public Opportunity createOpportunity(Contact contact) {

        printProductMenu();
        Product product = null;

        int input = inputAutowired.promptIntWithValidation("-> ", 3);
        if (input == 1) {
            product = Product.HYBRID;
        } else if (input == 2) {
            product = Product.FLATBED;
        } else if (input == 3) {
            product = Product.BOX;
        }

        int quantity = inputAutowired.promptIntWithCheck("Number of products for this Opportunity", Integer.MAX_VALUE);

        Opportunity newOpportunity = new Opportunity(product, quantity, contact, OPEN);

        printHeading("\n New Opportunity: \n" + newOpportunity);

        opportunityRepository.save(newOpportunity);
        return newOpportunity;
    }

    public void printProductMenu() {
        clearConsole();
        printHeading(" \n Choose a product for this opportunity? \n ");
        System.out.println("(1) HYBRID");
        System.out.println("(2) FLATBED");
        System.out.println("(3) BOX");
    }
}




//    public void changeStatus() {
//
//        if (!opportunityList.isEmpty()) {
//            Opportunity foundOpportunity = null;
//            int newInput;
//
//            for (Opportunity opportunity : opportunityList) {
//                System.out.println("Opportunity ID: " + opportunity.getId() + " -> Decision Maker: " +
//                        opportunity.getDecisionMaker().getName());
//            }
//
//            String input = Input.promptTextWithValidation("Input the ID of the Opportunity to change status",
//                    List.of(Validation.OPPORTUNITY));
//
//
//            for (Opportunity opportunity : opportunityList) {
//                if (input.equals(opportunity.getId())) {
//                    foundOpportunity = opportunity;
//
//                    printStatusMenu(foundOpportunity.getId());
//                    newInput = promptIntWithValidation("-> ", 3);
//
//                    if (newInput == 1) {
//                        foundOpportunity.setStatus(OPEN);
//                    } else if (newInput == 2) {
//                        foundOpportunity.setStatus(CLOSED_WON);
//                    } else if (newInput == 3) {
//                        foundOpportunity.setStatus(CLOSED_LOST);
//                    }
//
//                    printHeading("\nSuccessfully updated Opportunity Status: \n " + foundOpportunity);
//
//                    Utils.anythingToContinue();
//                    Utils.clearConsole();
//                    Navigation.startNavigation();
//                }
//            }
//
//
//        } else {
//            Utils.printLikeError("No Opportunities in the database, please create one");
//            Utils.anythingToContinue();
//            Utils.clearConsole();
//            Navigation.startNavigation();
//        }
//
//    }
//
//

//
//
//    public void printStatusMenu(String id) {
//        printHeading(" \n Choose the status for opportunity " + id + " \n ");
//        System.out.println("(1) OPEN");
//        System.out.println("(2) CLOSED WON");
//        System.out.println("(3) CLOSED LOST");
//    }
