package com.ironhack.TheDataLayerHomework3.menu;

import org.springframework.stereotype.Component;

@Component
public class AccountMenu {
}

//    static Map<String, Account> contactMap = new HashMap<>();
//
//    /**
//     * @param contact
//     * @param opportunity
//     */
//    public void createAccount(Contact contact, Opportunity opportunity) {
//        Industry industry = null;
//
//        clearConsole();
//        printHeading("Please input the following Account information");
//
//        industry = selectIndustry();
//
//        int employeeCount = promptIntWithCheck("Number of employees",  Integer.MAX_VALUE);
//
//        String city = Input.promptTextWithValidation("City of Account", List.of(Validation.STRING));
//
//        String country = Input.promptTextWithValidation("Country of Account", List.of(Validation.COUNTRY));
//
//
//        Account newAccount = new Account(industry, employeeCount, city, country);
//        newAccount.getContactList().add(contact);
//        newAccount.getOpportunityList().add(opportunity);
//

//        TODO add new Account to Contact -- accountId

//        printHeading("\n Successfully created: \n"+ newAccount);
//    }
//
//    private Industry selectIndustry() {
//        String typesAccountMenu = "\n (1) Produce \n (2) Ecommerce \n (3) Manufacturing \n (4) Medical \n (5) Other";
//        Industry industry = null;
//        int input = Input.promptIntWithValidation("Select type of industry" + typesAccountMenu, 5);
//
//        switch (input) {
//            case 1 -> industry = Industry.PRODUCE;
//            case 2 -> industry = Industry.ECOMMERCE;
//            case 3 -> industry = Industry.MANUFACTURING;
//            case 4 -> industry = Industry.MEDICAL;
//            case 5 -> industry = Industry.OTHER;
//        }
//        return industry;
//
//    }