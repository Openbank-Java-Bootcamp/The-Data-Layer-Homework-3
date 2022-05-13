package com.ironhack.TheDataLayerHomework3.queries;

import com.ironhack.TheDataLayerHomework3.enums.Product;
import com.ironhack.TheDataLayerHomework3.enums.Status;
import com.ironhack.TheDataLayerHomework3.menu.AccountMenu;
import com.ironhack.TheDataLayerHomework3.menu.SalesRepMenu;
import com.ironhack.TheDataLayerHomework3.models.*;
import com.ironhack.TheDataLayerHomework3.repository.*;
import com.ironhack.TheDataLayerHomework3.utils.Input;
import com.ironhack.TheDataLayerHomework3.utils.Validator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.ironhack.TheDataLayerHomework3.enums.Industry.ECOMMERCE;
import static org.junit.jupiter.api.Assertions.*;

class QueriesTest {

    @Autowired
    private Validator validator;
    @Autowired
    private Input inputAutowired;
    @Autowired
    private SalesRepMenu salesRepMenu;
    @Autowired
    private AccountMenu accountMenu;

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

        Opportunity opportunity1 = new Opportunity(Product.BOX, 95, contact1, Status.OPEN);
        Opportunity opportunity2 = new Opportunity(Product.FLATBED, 21, contact2, Status.OPEN);
        Opportunity opportunity3 = new Opportunity(Product.FLATBED, 1, contact2, Status.OPEN);
        opportunityRepository.saveAll(List.of(opportunity1, opportunity2, opportunity3));

        Account account1 = new Account(ECOMMERCE, 22, "Popinolandia", "France",
                List.of(contact1), List.of(opportunity1));
        Account account2 = new Account(ECOMMERCE, 3, "Sierra Nevada", "Italy",
                List.of(contact2), List.of(opportunity2));
        Account account3 = new Account(ECOMMERCE, 10, "Sierra Nevada", "Italy",
                List.of(contact2), List.of(opportunity1));
        accountRepository.saveAll(List.of(account1, account2, account3));

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
    void minEmployeeCount() {
        assertEquals(3, accountRepository.minEmployee());
        assertNotEquals(22, accountRepository.minEmployee());
    }

    @Test
    void maxEmployeeCount() {
        assertEquals(22, accountRepository.maxEmployee());
        assertNotEquals(3, accountRepository.maxEmployee());
    }

    @Test
    void medianEmployeeCount() {
        assertEquals(10, accountRepository.medianEmployee());
        assertNotEquals(8.6, accountRepository.medianEmployee());
    }

    @Test
    void meanEmployeeCount() {
        assertEquals(11.66, accountRepository.avgEmployee());
        assertNotEquals(3, accountRepository.avgEmployee());
    }

    @Test
    void minQuantityProductCount() {
        assertEquals(1, opportunityRepository.minQuantity());
        assertNotEquals(95, opportunityRepository.minQuantity());
    }

    @Test
    void maxQuantityProductCount() {
        assertEquals(95, opportunityRepository.maxQuantity());
        assertNotEquals(5, opportunityRepository.maxQuantity());
    }

    @Test
    void medianQuantityProductCount() {
        assertEquals(21, opportunityRepository.medianQuantity());
        assertNotEquals(3, opportunityRepository.medianQuantity());
    }

    @Test
    void meanQuantityProductCount() {
        assertEquals(39, opportunityRepository.avgQuantity());
        assertNotEquals(35, opportunityRepository.avgQuantity());
    }

    @Test
    void minOpportunityByAccountCount() {
        assertEquals(1, opportunityRepository.minOpportunities());
        assertNotEquals(95, opportunityRepository.minOpportunities());
    }

    @Test
    void maxOpportunityByAccountCount() {
        assertEquals(2, opportunityRepository.maxOpportunities());
        assertNotEquals(5, opportunityRepository.maxOpportunities());
    }

    @Test
    void medianOpportunityByAccountCount() {
        assertEquals(1.5, opportunityRepository.medianOpportunitiesByAccount());
        assertNotEquals(3, opportunityRepository.medianOpportunitiesByAccount());
    }

    @Test
    void meanOpportunityByAccountCount() {
        assertEquals(1.5, opportunityRepository.avgOpportunitiesByAccount());
        assertNotEquals(2, opportunityRepository.avgOpportunitiesByAccount());
    }
}