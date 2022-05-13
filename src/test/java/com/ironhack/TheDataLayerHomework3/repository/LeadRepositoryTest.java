package com.ironhack.TheDataLayerHomework3.repository;

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

class LeadRepositoryTest {
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
        Lead lead3 = new Lead(1, "Luci", 953341567L, "Luci@LuciProduceCo.com", "Luci Produce Co.",
                salesRep2);
        leadRepository.saveAll(List.of(lead1, lead2, lead3));

    }

    @AfterEach
    void tearDown() {
        salesRepRepository.deleteAll();
        leadRepository.deleteAll();
    }

    @Test
    void countBySalesRep() {
        SalesRep salesRep3 = new SalesRep("Sarita Dos");
        salesRepRepository.save(salesRep3);
        Lead lead4 = new Lead(1, "Luci", 953341567L, "Luci@LuciProduceCo.com", "Luci Produce Co.",
                salesRep3);
        Lead lead5 = new Lead(1, "Luci", 953341567L, "Luci@LuciProduceCo.com", "Luci Produce Co.",
                salesRep3);
        leadRepository.saveAll(List.of(lead4, lead5));

        assertEquals(2, leadRepository.countBySalesRep(salesRep3));
        assertNotEquals(3, leadRepository.countBySalesRep(salesRep3));
    }
}