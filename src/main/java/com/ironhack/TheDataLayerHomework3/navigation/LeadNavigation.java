package com.ironhack.TheDataLayerHomework3.navigation;


import com.ironhack.TheDataLayerHomework3.enums.Validation;
import com.ironhack.TheDataLayerHomework3.models.Contact;
import com.ironhack.TheDataLayerHomework3.models.Lead;
import com.ironhack.TheDataLayerHomework3.models.Opportunity;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import com.ironhack.TheDataLayerHomework3.utils.Input;
import com.ironhack.TheDataLayerHomework3.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.ironhack.TheDataLayerHomework3.navigation.OpportunityNavigation.createOpportunity;
import static com.ironhack.TheDataLayerHomework3.navigation.SalesRepNavigation.salesRepList;
import static com.ironhack.TheDataLayerHomework3.utils.Input.promptTextWithValidation;
import static com.ironhack.TheDataLayerHomework3.utils.Utils.*;

public class LeadNavigation {

    public static List<Lead> leadList = new ArrayList<>();
    static Contact currentContact;
    static Lead currentLead;
    static Opportunity currentOpportunity;
    static List<Contact> contactList = new ArrayList<>();

    public static void createNewLead() {

        //TODO add validation Sales Rep must be created before creating a Lead

        clearConsole();
        printHeading("Please input the following New Lead information");

        String newLeadName = promptTextWithValidation("Insert the Lead name", List.of(Validation.NAME));


        Long newLeadPhoneNumber = Long.valueOf(promptTextWithValidation("Insert the phone number", List.of(Validation.PHONE)));

        String newLeadEmail = promptTextWithValidation("Insert the email address", List.of(Validation.EMAIL));

        String companyName = promptTextWithValidation("Insert the Company name", List.of(Validation.STRING));

        printSeparator(20);

        //TODO add sales rep ID not full SalesRep obj ??
        leadList.add(new Lead(leadList.size() + 1, newLeadName, newLeadPhoneNumber, newLeadEmail, companyName, getSalesRepFromInputId()));

        currentLead = leadList.get(leadList.size() - 1);

        System.out.println(leadList.get(leadList.size() - 1).toString());

        Utils.anythingToContinue();
        Utils.clearConsole();
        Navigation.startNavigation();
    }

    private static SalesRep getSalesRepFromInputId() {
        SalesRep selectedSalesRep = null;

        if (!salesRepList.isEmpty()) {
            for (SalesRep salesRep : salesRepList) {
                System.out.println("Sales Rep ID: " + salesRep.getId() + " Name: " + salesRep.getName());
            }

            //TODO add sales rep ID not full SalesRep obj ??
            String salesRepId = promptTextWithValidation("Insert the Sales Rep ID", List.of(Validation.SALESREP));

            for (SalesRep salesRep : salesRepList) {
                if (salesRep.getId().equals(salesRepId)) selectedSalesRep = salesRep;
            }
        } else {
            Utils.printLikeError("No Sales reps in the database, please create one");
        }
        return selectedSalesRep;
    }

    public static void showLeads() {
        if (!leadList.isEmpty()) {
            Utils.printHeading("- Your current Leads - ");

            for (Lead lead : leadList) {
                if (!lead.getName().equals("Deleted Lead")) System.out.println(lead);
            }
        } else {
            Utils.printLikeError("No Leads in the database, please create one");
        }
        Utils.anythingToContinue();
        Utils.clearConsole();
        Navigation.startNavigation();
    }

    public static void lookUpLeadID() {
        if (!leadList.isEmpty()) {
            int input = Input.promptIntWithValidation("Insert the ID you'd like to check: ", leadList.size());

            System.out.println(leadList.get(input - 1).toString());

        } else {
            Utils.printLikeError("No Leads in the database, please create one");
        }
        Utils.anythingToContinue();
        Utils.clearConsole();
        Navigation.startNavigation();
    }


    public static Contact createContact(Lead lead) {
        Contact createdContact = null;

        createdContact = new Contact(lead);

        printHeading("\n Successfully created the contact: \n" + createdContact);

        return createdContact;
    }

    public static void convertLead() {

        boolean hasDeletedLead = false;

        for (Lead lead : leadList) {
            if (!lead.getName().equals("Deleted Lead")) hasDeletedLead = true;
        }

        if (!leadList.isEmpty() && hasDeletedLead) {
            clearConsole();

            int input = Input.promptIntWithCheck("Input the ID of the Lead you want to convert", leadList.size());

            Lead foundLead = leadList.get(input - 1);

            currentContact = createContact(foundLead);

            Utils.anythingToContinue();

            contactList.add(currentContact);

            currentOpportunity = createOpportunity(currentContact);

            deleteLead();
            Utils.anythingToContinue();

            AccountNavigation.createAccount(currentContact, currentOpportunity);

            deleteLead();

        } else {
            Utils.printLikeError("No Leads in the database, please create one");
        }
        Utils.anythingToContinue();
        Utils.clearConsole();
        Navigation.startNavigation();
    }

    public static void deleteLead() {
        printHeading("\n Successfully deleted: \n" + currentLead);

        leadList.get(currentLead.getLeadId() - 1).setName("Deleted Lead");
        leadList.get(currentLead.getLeadId() - 1).setPhoneNumber(0L);
        leadList.get(currentLead.getLeadId() - 1).setEmail("Deleted Lead email");
        leadList.get(currentLead.getLeadId() - 1).setCompanyName("Deleted Lead company name");

    }
}