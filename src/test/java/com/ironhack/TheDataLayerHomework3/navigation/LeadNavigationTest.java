package com.ironhack.TheDataLayerHomework3.navigation;

import  com.ironhack.TheDataLayerHomework3.classes.Lead;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ironhack.TheDataLayerHomework3.navigation.LeadNavigation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LeadNavigationTest {
       private Lead lead1 = new Lead();
    @BeforeEach //@BeforeAll
    public void setUp() {
        lead1 = new Lead( "Mike",956211567L,
                "MikeMichael@EmersonProduceCo.com" , "Emerson Produce Co.", 1);
        currentLead=lead1;
    }

    @AfterEach
    public void tearDown(){
        currentLead=null;
    }

//    @Test
//    public void createNewLead_() {
//        String newLeadName = "Mike";
//        Long newLeadPhoneNumber = 956211567;
//        String newLeadEmail = "MikeMichael@EmersonProduceCo.com"
//        String companyName = "Emerson Produce Co."
//
//         String email, companyName
//        leadList.add(new Lead(newLeadName, newLeadPhoneNumber, newLeadEmail, companyName, leadList.size()+1));
//        System.out.println(leadList.get(leadList.size()-1).toString());
//    }

    @Test
    public void createContact_Lead_Contact(){
        //Lead lead1 = new Lead( "Mike",956211567L, "MikeMichael@EmersonProduceCo.com" , "Emerson Produce Co.");
        assertEquals("Mike", createContact(lead1).getName());
        assertEquals(956211567L, createContact(lead1).getPhoneNumber());
        assertEquals("MikeMichael@EmersonProduceCo.com", createContact(lead1).getEmail());
    }

    @Test
    public void deleteLead_Lead(){
        leadList.add(lead1);
        System.out.println(leadList.size());
        deleteLead();
        assertEquals("Deleted Lead", currentLead.getName());
    }



}