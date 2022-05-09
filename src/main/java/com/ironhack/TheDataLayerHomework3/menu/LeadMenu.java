package com.ironhack.TheDataLayerHomework3.menu;

import com.ironhack.TheDataLayerHomework3.enums.Validation;
import com.ironhack.TheDataLayerHomework3.models.Contact;
import com.ironhack.TheDataLayerHomework3.models.Lead;
import com.ironhack.TheDataLayerHomework3.models.Opportunity;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import com.ironhack.TheDataLayerHomework3.repository.ContactRepository;
import com.ironhack.TheDataLayerHomework3.repository.LeadRepository;
import com.ironhack.TheDataLayerHomework3.repository.SalesRepRepository;
import com.ironhack.TheDataLayerHomework3.utils.Input;
import com.ironhack.TheDataLayerHomework3.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ironhack.TheDataLayerHomework3.utils.Utils.*;

@Component
public class LeadMenu {

    final private LeadRepository leadRepository;
    final private SalesRepRepository salesRepRepository;
    final private ContactRepository contactRepository;
    final private Input inputAutowired;
    final private OpportunityMenu opportunityMenu;
    private Lead currentLead;
    //final private AccountMenu accountMenu;

    public LeadMenu(LeadRepository leadRepository, SalesRepRepository salesRepRepository, ContactRepository contactRepository,
                    Input inputAutowired, OpportunityMenu opportunityMenu) {
        this.leadRepository = leadRepository;
        this.salesRepRepository = salesRepRepository;
        this.contactRepository = contactRepository;
        this.inputAutowired = inputAutowired;
        this.opportunityMenu = opportunityMenu;
    }


    public void menu() {
        int input = 0;

        while (input != 99) {

            input = inputAutowired.promptIntWithValidation("(1) Create New Lead \n(2) Show all Leads \n(3) Lookup Lead " +
                    "\n(4) Convert Lead \n(99) Go Back", 99);

            if (input == 1) createNewLead();
            else if (input == 2) showLeads();
            else if (input == 3) lookUpLeadID();
            else if (input == 4) convertLead();
        }
    }

    public void createNewLead() {
        var salesRepList = salesRepRepository.findAll();

        if (!salesRepList.isEmpty()) {
            clearConsole();
            printHeading("Please input the following New Lead information");

            String newLeadName = inputAutowired.promptTextWithValidation("Insert the Lead name", List.of(Validation.NAME));

            Long newLeadPhoneNumber = Long.valueOf(inputAutowired.promptTextWithValidation("Insert the phone number", List.of(Validation.PHONE)));

            String newLeadEmail = inputAutowired.promptTextWithValidation("Insert the email address", List.of(Validation.EMAIL));

            String companyName = inputAutowired.promptTextWithValidation("Insert the Company name", List.of(Validation.STRING));

            printSeparator(20);

            List<Lead> leadList = leadRepository.findAll();
            currentLead = (new Lead(leadList.size() + 1, newLeadName, newLeadPhoneNumber, newLeadEmail, companyName, getSalesRepFromInputId()));
            leadRepository.save(currentLead);

            System.out.println(currentLead.toString());

            anythingToContinue();
            clearConsole();
        } else {
            Utils.printLikeError("No Sales reps in the database, please create one before creating a Lead");
        }
    }

    public SalesRep getSalesRepFromInputId() {
        SalesRep selectedSalesRep = null;
        var salesRepList = salesRepRepository.findAll();

        if (!salesRepList.isEmpty()) {
            for (SalesRep salesRep : salesRepList) {
                System.out.println("Sales Rep ID: " + salesRep.getId() + " Name: " + salesRep.getName());
            }

            String salesRepId = inputAutowired.promptTextWithValidation("Insert the Sales Rep ID", List.of(Validation.SALESREP));

            for (SalesRep salesRep : salesRepList) {
                if (salesRep.getId().equals(salesRepId)) selectedSalesRep = salesRep;
            }
        } else {
            Utils.printLikeError("No Sales reps in the database, please create one");
        }
        return selectedSalesRep;
    }

    public void showLeads() {
        clearConsole();
        var leadList = leadRepository.findAll();

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
    }

    public void lookUpLeadID() {
        clearConsole();
        var leadList = leadRepository.findAll();

        if (!leadList.isEmpty()) {
            int input = inputAutowired.promptIntWithValidation("Insert the ID you'd like to check: ", leadList.size());

            System.out.println(leadList.get(input - 1).toString());

        } else {
            Utils.printLikeError("No Leads in the database, please create one");
        }
        anythingToContinue();
        clearConsole();
    }

    public void convertLead() {

        boolean hasDeletedLead = false;
        var leadList = leadRepository.findAll();

        for (Lead lead : leadList) {
            if (!lead.getName().equals("Deleted Lead")) hasDeletedLead = true;
        }

        if (!leadList.isEmpty() && hasDeletedLead) {
            clearConsole();

            int input = inputAutowired.promptIntWithValidation("Input the ID of the Lead you want to convert", leadList.size());

            Lead foundLead = leadList.get(input - 1);

            Contact currentContact = createContact(foundLead);

            anythingToContinue();

            Opportunity currentOpportunity = opportunityMenu.createOpportunity(currentContact);

            //deleteLead();
            anythingToContinue();

            //accountMenu.createAccount(currentContact, currentOpportunity);

        } else {
            Utils.printLikeError("No Leads in the database, please create one");
        }
        Utils.anythingToContinue();
        Utils.clearConsole();
    }

    public Contact createContact(Lead lead) {
        Contact createdContact = null;

        createdContact = new Contact(lead);
        contactRepository.save(createdContact);

        printHeading("\n Successfully created the contact: \n" + createdContact);

        return createdContact;
    }

}

//
//    public void deleteLead() {
//        printHeading("\n Successfully deleted: \n" + currentLead);
//
//        leadList.get(currentLead.getLeadId() - 1).setName("Deleted Lead");
//        leadList.get(currentLead.getLeadId() - 1).setPhoneNumber(0L);
//        leadList.get(currentLead.getLeadId() - 1).setEmail("Deleted Lead email");
//        leadList.get(currentLead.getLeadId() - 1).setCompanyName("Deleted Lead company name");
//
//    }