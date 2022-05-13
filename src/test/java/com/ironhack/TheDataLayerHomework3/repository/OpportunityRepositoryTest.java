package com.ironhack.TheDataLayerHomework3.repository;

import com.ironhack.TheDataLayerHomework3.enums.Industry;
import com.ironhack.TheDataLayerHomework3.enums.Product;
import com.ironhack.TheDataLayerHomework3.enums.Status;
import com.ironhack.TheDataLayerHomework3.models.*;
import com.ironhack.TheDataLayerHomework3.utils.Validator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.ironhack.TheDataLayerHomework3.enums.Industry.ECOMMERCE;
import static org.junit.jupiter.api.Assertions.*;

class OpportunityRepositoryTest {
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
    void countBySalesRepAndStatus() {
        List<SalesRep> salesRepList = salesRepRepository.findAll();

        assertEquals(1, opportunityRepository.countBySalesRepAndStatus(salesRepList.get(0), Status.OPEN));
        assertNotEquals(2, opportunityRepository.countBySalesRepAndStatus(salesRepList.get(0), Status.OPEN));
    }

    @Test
    void countBySalesRep() {
        List<SalesRep> salesRepList = salesRepRepository.findAll();

        assertEquals(1, opportunityRepository.countBySalesRep(salesRepList.get(0)));
        assertNotEquals(2, opportunityRepository.countBySalesRep(salesRepList.get(0)));
    }

    @Test
    void countByProduct() {
        assertEquals(1, opportunityRepository.countByProduct(Product.BOX));
        assertNotEquals(2, opportunityRepository.countByProduct(Product.FLATBED));
    }

    @Test
    void countByProductAndStatus() {
        assertEquals(1, opportunityRepository.countByProductAndStatus(Product.BOX, Status.OPEN));
        assertNotEquals(2, opportunityRepository.countByProductAndStatus(Product.FLATBED, Status.OPEN));
    }

    @Test
    void countByAccount_Country() {
        assertEquals(2, opportunityRepository.countByAccount_Country("Italy"));
        assertNotEquals(1, opportunityRepository.countByAccount_Country("Italy"));
    }

    @Test
    void countByStatusAndAccount_Country() {
        assertEquals(1, opportunityRepository.countByStatusAndAccount_Country( Status.OPEN, "France"));
        assertNotEquals(3, opportunityRepository.countByStatusAndAccount_Country(Status.OPEN, "France"));
    }

    @Test
    void countByAccount_City() {
        assertEquals(2, opportunityRepository.countByAccount_City("Sierra Nevada"));
        assertNotEquals(1, opportunityRepository.countByAccount_City("Sierra Nevada"));
    }

    @Test
    void countByStatusAndAccount_City() {
        assertEquals(2, opportunityRepository.countByStatusAndAccount_City( Status.OPEN, "Sierra Nevada"));
        assertNotEquals(3, opportunityRepository.countByStatusAndAccount_City(Status.OPEN, "Sierra Nevada"));
    }

    @Test
    void countByAccount_Industry() {
        assertEquals(2, opportunityRepository.countByAccount_Industry(Industry.ECOMMERCE));
        assertNotEquals(1, opportunityRepository.countByAccount_Industry(Industry.ECOMMERCE));
    }

    @Test
    void countByStatusAndAccount_Industry() {
        assertEquals(2, opportunityRepository.countByStatusAndAccount_Industry(Status.OPEN,Industry.ECOMMERCE));
        assertNotEquals(1, opportunityRepository.countByStatusAndAccount_Industry(Status.OPEN,Industry.ECOMMERCE));
    }

    @Test
    void maxQuantity() {
        assertEquals(95, opportunityRepository.maxQuantity());
        assertNotEquals(5, opportunityRepository.maxQuantity());
    }

    @Test
    void minQuantity() {
        assertEquals(1, opportunityRepository.minQuantity());
        assertNotEquals(95, opportunityRepository.minQuantity());
    }

    @Test
    void avgQuantity() {
        assertEquals(39, opportunityRepository.avgQuantity());
        assertNotEquals(35, opportunityRepository.avgQuantity());
    }

    @Test
    void medianQuantity() {
        assertEquals(21, opportunityRepository.medianQuantity());
        assertNotEquals(3, opportunityRepository.medianQuantity());
    }

    @Test
    void maxOpportunities() {
        assertEquals(2, opportunityRepository.maxOpportunities());
        assertNotEquals(5, opportunityRepository.maxOpportunities());
    }

    @Test
    void minOpportunities() {
        assertEquals(1, opportunityRepository.minOpportunities());
        assertNotEquals(95, opportunityRepository.minOpportunities());
    }
}