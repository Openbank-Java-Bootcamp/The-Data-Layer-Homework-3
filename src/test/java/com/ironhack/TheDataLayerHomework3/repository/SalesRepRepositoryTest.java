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

class SalesRepRepositoryTest {

    @Autowired
    private SalesRepRepository salesRepRepository;

    @BeforeEach
    void setUp() {
        SalesRep salesRep1 = new SalesRep("Sarita Wilson");
        SalesRep salesRep2 = new SalesRep("Sarita Dos");
        salesRepRepository.saveAll(List.of(salesRep1, salesRep2));
    }

    @AfterEach
    void tearDown() {
        salesRepRepository.deleteAll();
    }

    @Test
    void findById() {
        List<SalesRep> salesRepList = salesRepRepository.findAll();

        assertEquals("jhskja", salesRepRepository.findById(salesRepList.get(0).getSaleRepId()).get().getSaleRepId());
        assertNotEquals("jhskja", salesRepRepository.findById(salesRepList.get(0).getSaleRepId()).get().getSaleRepId());
    }

    @Test
    void findByName() {
        List<SalesRep> salesRepList = salesRepRepository.findAll();

        assertEquals(salesRepList.get(0), salesRepRepository.findByName("Sarita Wilson").get(0));
        assertNotEquals(salesRepList.get(1), salesRepRepository.findByName("Juan Ramón").get(0));
    }
}