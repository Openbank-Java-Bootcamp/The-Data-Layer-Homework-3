package com.ironhack.TheDataLayerHomework3.utils;


import com.ironhack.TheDataLayerHomework3.enums.Product;
import com.ironhack.TheDataLayerHomework3.enums.Status;
import com.ironhack.TheDataLayerHomework3.models.*;
import com.ironhack.TheDataLayerHomework3.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.ironhack.TheDataLayerHomework3.enums.Industry.ECOMMERCE;
import static com.ironhack.TheDataLayerHomework3.enums.Validation.PHONE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ValidatorTest {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private SalesRepRepository salesRepRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private Validator validator;

    @BeforeEach
    void setUp() {
        SalesRep salesRep1 = new SalesRep("Sarita Wilson");
        SalesRep salesRep2 = new SalesRep("Sarita Dos");
        salesRepRepository.saveAll(List.of(salesRep1, salesRep2));

        Lead lead1 = new Lead(1, "Mike", 956211567L, "MikeMichael@EmersonProduceCo.com", "Emerson Produce Co.",
                salesRep1);
        Lead lead2 = new Lead(1, "Luci", 953341567L, "Luci@LuciProduceCo.com", "Luci Produce Co.",
                salesRep2);
        leadRepository.saveAll(List.of(lead1, lead2));

        Contact contact1 = new Contact(lead1);
        Contact contact2 = new Contact(lead2);
        contactRepository.saveAll(List.of(contact1, contact2));

        Opportunity opportunity1 = new Opportunity(Product.BOX, 95621, contact1, Status.OPEN);
        Opportunity opportunity2 = new Opportunity(Product.BOX, 95621, contact2, Status.OPEN);
        opportunityRepository.saveAll(List.of(opportunity1, opportunity2));

        Account account1 = new Account(ECOMMERCE, 22, "Popinolandia", "France",
                List.of(contact1), List.of(opportunity1));
        Account account2 = new Account(ECOMMERCE, 22, "Sierra Nevada", "Italy",
                List.of(contact2), List.of(opportunity2));
        accountRepository.saveAll(List.of(account1, account2));

    }

    @AfterEach
    void tearDown() {
        salesRepRepository.deleteAll();
        leadRepository.deleteAll();
        contactRepository.deleteAll();
        opportunityRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    public void applyValidation_ValidationEnumAndStringInput_Boolean() {
        assertTrue(validator.applyValidation(PHONE, "(202) 555-0125"));
        assertFalse(validator.applyValidation(PHONE, "(hola) 555-0125"));
    }

    @Test
    public void isValidCountryName_String_Boolean() {
        assertTrue(validator.isValidCountryName("Argentina"));
        assertFalse(validator.isValidCountryName("Argentonia"));
    }

    @Test
    public void isValidName_String_Boolean() {
        assertTrue(validator.isValidName("Maria Antonia"));
        assertFalse(validator.isValidName("M@riantonia"));
    }

    @Test
    public void isValidOpportunityId_String_Boolean() {
        List<Opportunity> opportunityList = opportunityRepository.findAll();

        assertTrue(validator.isValidOpportunityId(opportunityList.get(0).getOpportunityId()));
        assertFalse(validator.isValidOpportunityId("1213L3"));
    }

    @Test
    public void isValidSalesRepId_String_Boolean() {
        List<SalesRep> salesRepList = salesRepRepository.findAll();

        assertTrue(validator.isValidSalesRepId(salesRepList.get(0).getSaleRepId()));
        assertFalse(validator.isValidSalesRepId("DD12133"));
    }


    @Test
    public void isValidLeadId_String_Boolean() {
        List<Lead> leadList = leadRepository.findAll();

        assertTrue(validator.isValidLeadId(String.valueOf(leadList.get(0).getLeadId())));
        assertFalse(validator.isValidLeadId("sdf133"));
    }


    @Test
    public void isValidAccountId_String_Boolean() {
        List<Account> accountList = accountRepository.findAll();

        assertTrue(validator.isValidAccountId(accountList.get(0).getAccountId()));
        assertFalse(validator.isValidAccountId("DD12133"));
    }


    @Test
    public void isValidEmail_String_Boolean() {
        assertTrue(validator.isValidEmail("Maria@Antonia.es"));
        assertFalse(validator.isValidEmail("miariantonia@"));
        assertFalse(validator.isValidEmail("miariantonia.es"));
        assertFalse(validator.isValidEmail("@miariantonia.es"));
    }

    @Test
    public void isValidCommand_String_Boolean() {
        assertTrue(validator.isValidCommand(" new LEAD"));
        assertFalse(validator.isValidCommand("niu lid"));
    }

    @Test
    public void isValidPhoneNumber_String_Boolean() {
        assertTrue(validator.applyValidation(PHONE, "(202) 555-0125"));
        assertFalse(validator.applyValidation(PHONE, "(adios) 555-0125"));
    }

}