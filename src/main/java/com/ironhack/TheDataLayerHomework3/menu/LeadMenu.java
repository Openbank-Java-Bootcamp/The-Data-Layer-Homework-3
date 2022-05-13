package com.ironhack.TheDataLayerHomework3.menu;

import com.ironhack.TheDataLayerHomework3.enums.Validation;
import com.ironhack.TheDataLayerHomework3.models.Account;
import com.ironhack.TheDataLayerHomework3.models.Contact;
import com.ironhack.TheDataLayerHomework3.models.Lead;
import com.ironhack.TheDataLayerHomework3.models.Opportunity;
import com.ironhack.TheDataLayerHomework3.repository.*;
import com.ironhack.TheDataLayerHomework3.utils.Colors;
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
    final private AccountRepository accountRepository;
    final private OpportunityRepository opportunityRepository;
    final private Input inputAutowired;
    final private OpportunityMenu opportunityMenu;
    final private SalesRepMenu salesRepMenu;
    final private AccountMenu accountMenu;

    public LeadMenu(LeadRepository leadRepository, SalesRepRepository salesRepRepository, ContactRepository contactRepository,
                    AccountRepository accountRepository, OpportunityRepository opportunityRepository, Input inputAutowired, OpportunityMenu opportunityMenu, SalesRepMenu salesRepMenu, AccountMenu accountMenu) {
        this.leadRepository = leadRepository;
        this.salesRepRepository = salesRepRepository;
        this.contactRepository = contactRepository;
        this.accountRepository = accountRepository;
        this.opportunityRepository = opportunityRepository;
        this.inputAutowired = inputAutowired;
        this.opportunityMenu = opportunityMenu;
        this.salesRepMenu = salesRepMenu;
        this.accountMenu = accountMenu;
    }


    public void menu() {
        int input = 0;

        while (input != 99) {
            clearConsole();
            input = inputAutowired.promptIntWithValidation(
                    "(1)" + Colors.BLUE + " Create New Lead " + Colors.RESET +
                    "\n(2)" + Colors.BLUE +  " Show all Leads " + Colors.RESET +
                    "\n(3)" + Colors.BLUE + " Lookup Lead " + Colors.RESET +
                    "\n(4)" + Colors.BLUE + " Convert Lead " + Colors.RESET +
                    "\n\n(99)" + Colors.RED + "  Go Back"+ Colors.RESET , 99);

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

            Lead currentLead = (new Lead(newLeadName, newLeadPhoneNumber, newLeadEmail, companyName, salesRepMenu.getSalesRepFromInputId()));
            leadRepository.save(currentLead);

            System.out.println(currentLead.toString());

            anythingToContinue();
            clearConsole();
        } else {
            Utils.printLikeError("No Sales reps in the database, please create one before creating a Lead");
        }
    }


    public void showLeads() {
        clearConsole();
        var leadList = leadRepository.findAll();

        if (!leadList.isEmpty()) {
            Utils.printHeading("- Your current Leads - ");

            for (Lead lead : leadList) {
                if (!lead.getName().equals("Deleted Lead")) System.out.println("* "+lead);
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
            String input = inputAutowired.promptTextWithValidation("\n\nInsert the ID you'd like to check:", List.of(Validation.LEAD));

            Lead foundLead = leadRepository.findById(Integer.valueOf(input)).get();

            System.out.println(foundLead);

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

            String input = inputAutowired.promptTextWithValidation("\n\nInput the ID of the Lead you want to convert", List.of(Validation.LEAD));

            Lead foundLead = leadRepository.findById(Integer.valueOf(input)).get();

            Contact currentContact = createContact(foundLead);

            anythingToContinue();

            Opportunity currentOpportunity = opportunityMenu.createOpportunity(currentContact);

            createAccountWithContactAndOpportunity(currentContact, currentOpportunity);


            deleteLead(foundLead);
            anythingToContinue();


        } else {
            Utils.printLikeError("No Leads in the database, please create one");
        }
//        Utils.anythingToContinue();
        Utils.clearConsole();
    }


    public void createAccountWithContactAndOpportunity(Contact currentContact, Opportunity currentOpportunity) {
        int inputAccount = inputAutowired.promptIntWithValidation(Colors.BLUE + "\n\nWould you like to create a new Account?" + Colors.RESET +
                "\n(1) Yes! \n(2) No, I want to add it to an existing one ", 2);

        if (inputAccount == 1) {
            Account createdAccount = accountMenu.createAccount(currentContact, currentOpportunity);

            currentContact.setAccount(createdAccount);
            contactRepository.save(currentContact);

            currentOpportunity.setAccount(createdAccount);
            opportunityRepository.save(currentOpportunity);
        }

        else if (inputAccount == 2) {

            accountMenu.showAllAccounts();

            String accountId = inputAutowired.promptTextWithValidation("Insert the Account ID", List.of(Validation.ACCOUNT));

            Account selectedAccount = accountRepository.findById(accountId).get();

            currentContact.setAccount(selectedAccount);
            contactRepository.save(currentContact);

            currentOpportunity.setAccount(selectedAccount);
            opportunityRepository.save(currentOpportunity);

            accountRepository.save(selectedAccount);

        }
    }


    public Contact createContact(Lead lead) {
        Contact createdContact = null;

        createdContact = new Contact(lead);
        contactRepository.save(createdContact);

        printHeading("\n Successfully created the contact: \n" + createdContact);

        return createdContact;
    }


    public void deleteLead(Lead foundLead) {
        printHeading("\n Successfully deleted the lead: \n" + foundLead);

        leadRepository.delete(foundLead);
    }

}