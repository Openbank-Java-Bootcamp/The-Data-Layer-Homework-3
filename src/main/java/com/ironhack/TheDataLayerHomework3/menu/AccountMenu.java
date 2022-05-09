package com.ironhack.TheDataLayerHomework3.menu;

import com.ironhack.TheDataLayerHomework3.enums.Industry;
import com.ironhack.TheDataLayerHomework3.enums.Validation;
import com.ironhack.TheDataLayerHomework3.models.Account;
import com.ironhack.TheDataLayerHomework3.models.Contact;
import com.ironhack.TheDataLayerHomework3.models.Opportunity;
import com.ironhack.TheDataLayerHomework3.models.SalesRep;
import com.ironhack.TheDataLayerHomework3.repository.AccountRepository;
import com.ironhack.TheDataLayerHomework3.utils.Input;
import com.ironhack.TheDataLayerHomework3.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ironhack.TheDataLayerHomework3.utils.Utils.clearConsole;
import static com.ironhack.TheDataLayerHomework3.utils.Utils.printHeading;

@Component
public class AccountMenu {
    final private Input inputAutowired;
    final private AccountRepository accountRepository;

    public AccountMenu(Input inputAutowired, AccountRepository accountRepository) {
        this.inputAutowired = inputAutowired;
        this.accountRepository = accountRepository;
    }


//    static Map<String, Account> contactMap = new HashMap<>();

    /**
     * @param contact
     * @param opportunity
     */
    public void createAccount(Contact contact, Opportunity opportunity) {
        Industry industry = null;

        clearConsole();
        printHeading("Please input the following Account information");

        industry = selectIndustry();

        int employeeCount =  inputAutowired.promptIntWithCheck("Number of employees", Integer.MAX_VALUE);

        String city = inputAutowired.promptTextWithValidation("City of Account", List.of(Validation.STRING));

        String country = inputAutowired.promptTextWithValidation("Country of Account", List.of(Validation.COUNTRY));


        Account newAccount = new Account(industry, employeeCount, city, country);
        newAccount.getContactList().add(contact);
        newAccount.getOpportunityList().add(opportunity);

        accountRepository.save(newAccount);

        printHeading("\n Successfully created: \n"+ newAccount);
    }



    private Industry selectIndustry() {
        String typesAccountMenu = "\n (1) Produce \n (2) Ecommerce \n (3) Manufacturing \n (4) Medical \n (5) Other";
        Industry industry = null;
        int input = inputAutowired.promptIntWithValidation("Select type of industry" + typesAccountMenu, 5);

        switch (input) {
            case 1 -> industry = Industry.PRODUCE;
            case 2 -> industry = Industry.ECOMMERCE;
            case 3 -> industry = Industry.MANUFACTURING;
            case 4 -> industry = Industry.MEDICAL;
            case 5 -> industry = Industry.OTHER;
        }
        return industry;


    }
    public void showAllAccounts() {
        clearConsole();

        List<Account> accountList = accountRepository.findAll();

        if (!accountList.isEmpty()) {
            Utils.printHeading("- Your current Accounts - ");

            for (Account account : accountList) {
                System.out.println(account);
            }
        } else {
            Utils.printLikeError("No Accounts in the database, please create one");
        }
        Utils.anythingToContinue();
        Utils.clearConsole();
    }


}